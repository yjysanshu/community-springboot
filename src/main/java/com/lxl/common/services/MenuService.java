package com.lxl.common.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lxl.admin.models.Menu;
import com.lxl.admin.models.MenuBase;
import com.lxl.common.consts.AdminResourceConst;
import com.lxl.common.consts.AdminRoleConst;
import com.lxl.common.mapper.AdminResourceMapper;
import com.lxl.common.mapper.AdminRoleUserMapper;
import com.lxl.common.models.AdminResource;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminUser;
import com.lxl.common.models.relevant.AdminRoleUserRelevant;
import com.lxl.common.util.ConsoleUtil;
import com.lxl.common.util.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service
public class MenuService extends BaseService {

    @Autowired
    private AdminResourceMapper adminRMapper;
    @Autowired
    private AdminRoleUserMapper adminRoleUserMapper;
    @Autowired
    private AdminPrivilegeService adminPrivilegeService;

    private List<AdminResource> adminResourceListAll;

    public List<AdminResource> getAll() {
        return new ArrayList<>();
    }

    /**
     * 获取用户菜单
     * @return 菜单信息
     * @throws IOException -
     */
    public List<Menu> getMenuByUser() throws IOException {
        AdminUser adminUser = this.getCurrentUser();
        List<AdminRoleUserRelevant> relevantList = adminRoleUserMapper.findAllByUserId(adminUser.getAdminUserId());
        if (relevantList.size() <= 0) {
            return new ArrayList<>();
        }
        List<Integer> roleIds = new ArrayList<>();
        for (AdminRoleUserRelevant relevant : relevantList) {
            roleIds.add(relevant.getAdminRole().getAdminRoleId());
        }
        ConsoleUtil.formatPrint(roleIds);
        return adminPrivilegeService.getMenuByRoleList(roleIds);
    }

    /**
     * 递归获取菜单
     * @param parentId 父级ID
     * @return 菜单
     * @throws IOException 解析JSON错误
     */
    public List<Menu> getMenuByParentId(Integer parentId) throws IOException {
        if (parentId == AdminResourceConst.PARENT_ID_DEFAULT) {
            adminResourceListAll = adminRMapper.findByParams(new AdminResource());
        }
        List<AdminResource> listResource;
        //本地有使用本地的数据
        if (adminResourceListAll.size() > 0) {
            listResource = this.getMenuByListAndParentId(parentId);
        } else {
            listResource = adminRMapper.findByParentId(parentId);
        }
        List<Menu> list = new ArrayList<>();
        for (AdminResource adminResource : listResource) {
            Menu menu = formatInfoDetail(adminResource);
            menu.setChildren(this.getMenuByParentId(menu.getId()));
            list.add(menu);
        }
        return list;
    }

    /**
     * 查询本地list信息
     * @param parentId -
     * @return -
     */
    public List<AdminResource> getMenuByListAndParentId(Integer parentId) {
        List<AdminResource> adminResourceList = new ArrayList<>();
        for (AdminResource adminResource : adminResourceListAll) {
            if (adminResource.getAdminResourceParentId().equals(parentId)) {
                adminResourceList.add(adminResource);
            }
        }
        return adminResourceList;
    }

    /**
     * 获取所有的菜单
     * @param request 请求信息
     * @return 菜单信息
     * @throws IOException json解析错误
     */
    public List<MenuBase> getList(Menu request) throws IOException {
        List<AdminResource> listAdminResource = adminRMapper.findByParams(formatModelDetail(request));
        List<MenuBase> listMenu = new ArrayList<>();
        for (AdminResource adminResource : listAdminResource) {
            MenuBase menu = formatInfoBaseDetail(adminResource);
            listMenu.add(menu);
        }

        return listMenu;
    }

    /**
     * 获取所有的父级菜单
     * @return 菜单信息
     * @throws IOException json解析错误
     */
    public List<MenuBase> getFatherList() throws IOException {
        List<AdminResource> listAdminResource = adminRMapper.findByParentId(0);
        List<MenuBase> listMenu = new ArrayList<>();
        for (AdminResource adminResource : listAdminResource) {
            MenuBase menu = formatInfoBaseDetail(adminResource);
            listMenu.add(menu);
        }

        return listMenu;
    }

    /**
     * 保存菜单
     * @param menu 请求信息
     * @return 受影响的行数
     * @throws JsonProcessingException 解析JSON错误
     */
    public Integer save(Menu menu) throws JsonProcessingException {
        AdminResource adminResource;
        if (menu.getId() != null) {
            adminResource = adminRMapper.findOneById(menu.getId());
        } else {
            adminResource = new AdminResource();
            adminResource.setAdminResourceType(0);
        }
        adminResource.setAdminResourceParentId(menu.getParentId());
        adminResource.setAdminResourceTarget(menu.getUrl());
        adminResource.setAdminResourceData(JSONUtil.menuToJson(menu));
        if (menu.getId() != null) {
            return adminRMapper.updateByIdAndParams(adminResource);
        } else {
            return adminRMapper.insertByParams(adminResource);
        }
    }

    /**
     * 是否展示菜单: 当所有子菜单都未被勾选时，不展示当前菜单，返回 false
     *              其他情况都需要展示菜单，均返回 true
     * @param menu 菜单
     * @return true-展示菜单
     */
    public Boolean isDisplay(Menu menu) {
        if (menu.getChecked()) {
            return true;
        }
        for (Menu cMenu: menu.getChildren()) {
            if (this.isDisplay(cMenu)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否选中父菜单
     * @param menu -
     * @return -
     */
    public Boolean isChecked(Menu menu) {
        for (Menu cMenu : menu.getChildren()) {
            if (!this.isChecked(cMenu)) {
                return false;
            }
        }
        return menu.getChecked();
    }

    public List<AdminResource> getByPrivilegeRoleIdAndType(Integer roleId, String resourceType) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        map.put("resourceType", resourceType);
        return adminRMapper.findByJoinPrivilegeAndParams(map);
    }

    public AdminResource getOneById(Integer id) {
        return adminRMapper.findOneById(id);
    }

    private MenuBase formatInfoBaseDetail(AdminResource adminResource) throws IOException {
        Map map = JSONUtil.jsonToMap(adminResource.getAdminResourceData());
        MenuBase menu = new MenuBase();
        menu.setId(adminResource.getAdminResourceId());
        menu.setUrl(adminResource.getAdminResourceTarget());
        menu.setParentId(adminResource.getAdminResourceParentId());
        menu.setTitle((String) map.get("title"));
        menu.setPath((String) map.get("path"));
        menu.setSeq((Integer) map.get("seq"));
        menu.setIcon((String) map.get("icon"));
        menu.setType((String) map.get("type"));
        return menu;
    }

    private Menu formatInfoDetail(AdminResource adminResource) throws IOException {
        Map map = JSONUtil.jsonToMap(adminResource.getAdminResourceData());
        Menu menu = new Menu();
        menu.setId(adminResource.getAdminResourceId());
        menu.setUrl(adminResource.getAdminResourceTarget());
        menu.setParentId(adminResource.getAdminResourceParentId());
        menu.setTitle((String) map.get("title"));
        menu.setPath((String) map.get("path"));
        menu.setSeq((Integer) map.get("seq"));
        menu.setIcon((String) map.get("icon"));
        menu.setType((String) map.get("type"));
        menu.setChecked(true);
        return menu;
    }

    private AdminResource formatModelDetail(Menu menu) {
        AdminResource adminResource = new AdminResource();
        adminResource.setAdminResourceId(menu.getId());
        adminResource.setAdminResourceParentId(menu.getParentId());
        adminResource.setAdminResourceTarget(menu.getUrl());
        return adminResource;
    }
}
