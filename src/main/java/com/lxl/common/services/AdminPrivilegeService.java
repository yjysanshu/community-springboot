package com.lxl.common.services;

import com.lxl.admin.models.Menu;
import com.lxl.admin.models.request.AdminPrivilegeRequest;
import com.lxl.admin.models.request.PrivilegeRequest;
import com.lxl.admin.models.response.AdminPrivilegeResponse;
import com.lxl.common.consts.AdminResourceConst;
import com.lxl.common.consts.AdminRoleConst;
import com.lxl.common.mapper.AdminPrivilegeMapper;
import com.lxl.common.models.AdminPrivilege;
import com.lxl.common.models.AdminResource;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminRoleUser;
import com.lxl.common.util.ConsoleUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminPrivilegeService {

    @Autowired
    private AdminPrivilegeMapper adminPrivilegeMapper;

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminRoleUserService adminRoleUserService;

    @Autowired
    private MenuService menuService;

    /**
     * 获取角色的菜单
     * @param roleId 角色ID
     * @return 菜单信息
     * @throws IOException -
     */
    public Map getRoleMenu(Integer roleId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        AdminRole adminRole = adminRoleService.getOneById(roleId);
        if (adminRole == null) {
            return null;
        }

        List<Menu> list= this.getMenuByRoleList(Collections.singletonList(AdminResourceConst.PARENT_ID_DEFAULT));
        ConsoleUtil.formatPrint(list.toString());
        list = this.checkMenuPrivilege(list, Collections.singletonList(roleId));

        List<Integer> listCheckedKeys = new ArrayList<>(), listExpandedKeys = new ArrayList<>();
        for (Menu menu : list) {
            if (menuService.isChecked(menu)) {
                listCheckedKeys.add(menu.getId());
            }
            if (menuService.isDisplay(menu)) {
                listExpandedKeys.add(menu.getId());
            }
            for (Menu cMenu : menu.getChildren()) {
                if (cMenu.getChecked()) {
                    listCheckedKeys.add(cMenu.getId());
                }
            }
        }

        ConsoleUtil.formatPrint(list.toString());
        map.put("list", list);
        map.put("checkedKeys", listCheckedKeys);
        map.put("expandedKeys", listExpandedKeys);
        return map;
    }

    /**
     * 获取给定角色的菜单列表
     * @param roleIds 角色ID
     * @return 角色的菜单
     * @throws IOException -
     */
    public List<Menu> getMenuByRoleList(List<Integer> roleIds) throws IOException {
        List<Menu> list = menuService.getMenuByParentId(AdminResourceConst.PARENT_ID_DEFAULT);
        if (roleIds.contains(AdminRoleConst.ROLE_SUPER_ADMIN)) {
            return list;
        }
        list = this.checkMenuPrivilege(list, roleIds);
        List<Menu> removeList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (!menuService.isDisplay(list.get(i))) {
                ConsoleUtil.formatPrint("i = " + i);
                removeList.add(list.get(i));
                continue;
            }
            List<Menu> listChildren = list.get(i).getChildren();
            List<Menu> removeChildrenList = new ArrayList<>();
            for (int j = 0; j < listChildren.size(); j++) {
                if (!menuService.isDisplay(listChildren.get(j))) {
                    ConsoleUtil.formatPrint("j = " + j);
                    removeChildrenList.add(listChildren.get(j));
                }
            }
            for (Menu menu : removeChildrenList) {
                listChildren.remove(menu);
            }
        }
        for (Menu menu : removeList) {
            list.remove(menu);
        }

        return list;
    }

    /**
     * 检查角色对菜单的权限
     * @param listMenu 菜单
     * @param roleIds 角色ID
     * @return array
     */
    public List<Menu> checkMenuPrivilege(List<Menu> listMenu, List<Integer> roleIds)
    {
        for (Menu menu : listMenu) {
            menu.setChecked(this.hasRoleListPrivilege(roleIds, menu.getId()));
            menu.setChildren(this.checkMenuPrivilege(menu.getChildren(), roleIds));
        }
        return listMenu;
    }

    /**
     * 验证角色是否有菜单权限
     * @param roleIds 角色ID
     * @param resourceId 菜单ID
     * @return true-有权限
     */
    private Boolean hasRoleListPrivilege(List<Integer> roleIds, Integer resourceId) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleIds", roleIds);
        map.put("resourceId", resourceId);
        return adminPrivilegeMapper.findByRoleIdsAndResourceId(map) > 0;
    }

    @Transactional
    public Boolean savePrivilege(PrivilegeRequest request) {
        //判断当前用户是否有权限保存角色和资源的关联关系
        this.checkOperatorPrivilege(request.getRoleId(), request.getResourceIds());

        // 获取当前角色的权限数组
        List<AdminResource> listOld = menuService.getByPrivilegeRoleIdAndType(request.getRoleId(), request.getResourceType());

        List<Integer> resourceIds = new ArrayList<>();
        for (AdminResource adminResource : listOld) {
            resourceIds.add(adminResource.getAdminResourceId());
        }
        // 删除当前角色的权限
        this.deleteByRoleAndResource(resourceIds, request.getRoleId());

        // 重建新的权限关系
        for (Integer resourceId : request.getResourceIds()) {
            AdminPrivilege adminPrivilege = new AdminPrivilege();
            adminPrivilege.setAdminPrivilegeAdminRoleId(request.getRoleId());
            adminPrivilege.setAdminPrivilegeAdminResourceId(resourceId);
            adminPrivilege.setAdminPrivilegeCreateAt(new Date());
            adminPrivilegeMapper.insertByParams(adminPrivilege);
        }

        // 去除后代角色与当前角色的资源差集
        this.removeDescendantResource(request.getRoleId(), request.getResourceType(), request.getResourceIds());

        return true;

    }

    public Boolean deleteByRoleAndResource(List<Integer> resourceIds, Integer roleId) {
        if (resourceIds.size() <= 0 || roleId == null) {
            return true;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("resourceIds", resourceIds);
        return adminPrivilegeMapper.deleteByRoleIdAndResourceId(map) > 0;
    }

    public void removeDescendantResource(Integer roleId, String resourceType, List<Integer> resourceIds) {
        List<AdminRole> childrenAdminRolesList = adminRoleService.getByParentId(roleId);

        for (AdminRole adminRole : childrenAdminRolesList) {
            List<AdminPrivilege> listPrivilege = adminPrivilegeMapper.findByRoleId(adminRole.getAdminRoleId());
            for (AdminPrivilege adminPrivilege : listPrivilege) {
                AdminResource adminResource = menuService.getOneById(adminPrivilege.getAdminPrivilegeAdminResourceId());
                if ((adminResource != null) &&
                        adminResource.getAdminResourceType().equals(resourceType) &&
                        !Collections.singletonList(resourceIds).contains(adminPrivilege.getAdminPrivilegeAdminResourceId())) {
                    adminPrivilegeMapper.deleteOneById(adminPrivilege.getAdminPrivilegeId());
                }
                this.removeDescendantResource(adminRole.getAdminRoleId(), resourceType, resourceIds);
            }
        }
    }

    public Boolean checkOperatorPrivilege(Integer roleId, List<Integer> resourceIds) {
        List<AdminRoleUser> listRoleUser = adminRoleUserService.getAdminRolesByUserId(1);
        AdminRole ancestorRole = adminRoleUserService.isAncestorRole(roleId, listRoleUser);
        if (ancestorRole == null) {
            System.out.println("这个没有 xxxxxxxxxxxxxx");
            return false;
        }
        return this.judgeResourceOptional(ancestorRole.getAdminRoleId(), resourceIds);
    }

    /**
     * 判断资源是否都存在于给定角色的可操作资源列表
     * @param roleId
     * @param listResourceId
     * @return
     */
    public Boolean judgeResourceOptional(Integer roleId, List<Integer> listResourceId)
    {
        List<AdminPrivilege> list = adminPrivilegeMapper.findByRoleId(roleId);

        // 判断资源是否都存在于给定角色的可操作资源列表
        for (AdminPrivilege adminPrivilege : list) {
            for (Integer resourceId : listResourceId) {
                if (!resourceId.equals(adminPrivilege.getAdminPrivilegeAdminResourceId())) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<AdminPrivilegeResponse> getList(AdminPrivilegeRequest request) {
        AdminPrivilege adminPrivilegeSearch = formatModelDetail(request);
        List<AdminPrivilege> listAdminPrivilege = adminPrivilegeMapper.findByParams(adminPrivilegeSearch);
        List<AdminPrivilegeResponse> list = new ArrayList<>();
        for (AdminPrivilege adminPrivilege : listAdminPrivilege) {
            AdminPrivilegeResponse adminPrivilegeResponse = formatResponseDetail(adminPrivilege);
            list.add(adminPrivilegeResponse);
        }
        return list;
    }

    public Integer getTotal(AdminPrivilegeRequest request) {
        AdminPrivilege adminPrivilegeSearch = formatModelDetail(request);
        return adminPrivilegeMapper.findTotalByParams(adminPrivilegeSearch);
    }

    public Integer save(AdminPrivilegeRequest request) {
        AdminPrivilege adminPrivilege;
        if (request.getId() != null) {
            adminPrivilege = adminPrivilegeMapper.findOneById(request.getId());
        } else {
            adminPrivilege = new AdminPrivilege();
            adminPrivilege.setAdminPrivilegeCreateAt(new Date());
        }
        adminPrivilege.setAdminPrivilegeAdminResourceId(request.getAdminResourceId());
        adminPrivilege.setAdminPrivilegeAdminRoleId(request.getAdminRoleId());
        adminPrivilege.setAdminPrivilegeType(request.getType());
        adminPrivilege.setAdminPrivilegeCreateBy(request.getCreateBy());
        adminPrivilege.setAdminPrivilegeUpdateBy(request.getUpdateBy());
        if (request.getId() != null) {
            return adminPrivilegeMapper.updateByIdAndParams(adminPrivilege);
        } else {
            return adminPrivilegeMapper.insertByParams(adminPrivilege);
        }
    }

    public Integer delete(Integer id) {
        return adminPrivilegeMapper.deleteOneById(id);
    }

    private AdminPrivilegeResponse formatResponseDetail(AdminPrivilege adminPrivilege) {
        AdminPrivilegeResponse response = new AdminPrivilegeResponse();
        response.setId(adminPrivilege.getAdminPrivilegeId());
        response.setAdminResourceId(adminPrivilege.getAdminPrivilegeAdminResourceId());
        response.setAdminRoleId(adminPrivilege.getAdminPrivilegeAdminRoleId());
        response.setType(adminPrivilege.getAdminPrivilegeType());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminPrivilege.getAdminPrivilegeCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminPrivilege.getAdminPrivilegeUpdateAt()));
        response.setCreateBy(adminPrivilege.getAdminPrivilegeCreateBy());
        response.setUpdateBy(adminPrivilege.getAdminPrivilegeUpdateBy());
        return response;
    }

    private AdminPrivilege formatModelDetail(AdminPrivilegeRequest request) {
        AdminPrivilege adminPrivilege = new AdminPrivilege();
        adminPrivilege.setAdminPrivilegeId(request.getId());
        adminPrivilege.setAdminPrivilegeAdminResourceId(request.getAdminResourceId());
        adminPrivilege.setAdminPrivilegeAdminRoleId(request.getAdminRoleId());
        adminPrivilege.setAdminPrivilegeType(request.getType());
        adminPrivilege.setAdminPrivilegeCreateAt(request.getCreateAt());
        adminPrivilege.setAdminPrivilegeUpdateAt(request.getUpdateAt());
        adminPrivilege.setAdminPrivilegeCreateBy(request.getCreateBy());
        adminPrivilege.setAdminPrivilegeUpdateBy(request.getUpdateBy());
        return adminPrivilege;
    }
}