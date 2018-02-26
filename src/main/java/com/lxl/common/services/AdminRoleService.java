package com.lxl.common.services;

import com.lxl.admin.models.request.AdminRoleRequest;
import com.lxl.admin.models.response.AdminRoleResponse;
import com.lxl.common.consts.AdminRoleConst;
import com.lxl.common.mapper.AdminRoleMapper;
import com.lxl.common.models.AdminRole;
import com.lxl.common.models.AdminRoleUser;
import com.lxl.common.models.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AdminRoleService extends BaseService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminRoleUserService adminRoleUserService;

    public List<AdminRoleResponse> getList(AdminRoleRequest request) {
        AdminRole adminRoleSearch = formatModelDetail(request);
        List<AdminRole> listAdminRole = adminRoleMapper.findByParams(adminRoleSearch);
        List<AdminRoleResponse> list = new ArrayList<>();
        for (AdminRole adminRole : listAdminRole) {
            AdminRoleResponse adminRoleResponse = formatResponseDetail(adminRole);
            list.add(adminRoleResponse);
        }
        return list;
    }

    public AdminRole getOneById(Integer roleId) {
        return adminRoleMapper.findOneById(roleId);
    }

    /**
     * 获取用户的角色
     * @return 用户的角色
     */
    public List<AdminRoleResponse> manageRole() {
        AdminUser adminUser = this.getCurrentUser();
        List<AdminRoleUser> listRole = adminRoleUserService.getAdminRolesByUserId(adminUser.getAdminUserId());
        List<AdminRole> listGroupRoles = new ArrayList<>();
        for (AdminRoleUser adminRoleUser : listRole) {
            AdminRole adminRole = adminRoleMapper.findOneById(adminRoleUser.getAdminRoleUserAdminRoleId());

            if (adminRole.getAdminRoleType() == AdminRoleConst.ROLE_TYPE_GROUP) {
                listGroupRoles.add(adminRole);
            }
        }

        return this.nestRole(listGroupRoles, 0);
    }

    /**
     * 获取角色
     * @param listGroupRoles
     * @param depth
     * @return
     */
    private List<AdminRoleResponse> nestRole(List<AdminRole> listGroupRoles, Integer depth) {
        List<AdminRoleResponse> list = new ArrayList<>();
        for (AdminRole adminRole : listGroupRoles) {
            List<AdminRole> listRole = adminRoleMapper.findRolesByParentId(adminRole.getAdminRoleId());

            AdminRoleResponse adminRoleResponse = formatResponseDetail(adminRole);
            adminRoleResponse.setLevel(depth);
            adminRoleResponse.setIsExpand(false);

            if (listRole.size() > 0) {
                adminRoleResponse.setIsParent(true);
                adminRoleResponse.setChildren(this.nestRole(listRole, depth + 1));
            } else {
                adminRoleResponse.setIsParent(false);
                adminRoleResponse.setChildren(new ArrayList<>());
            }
            list.add(adminRoleResponse);
        }
        return list;
    }

    /**
     * 通过roleId查询
     * @param roleIds
     * @return
     */
    public List<AdminRole> getByRoleIds(List<Integer> roleIds) {
        if (roleIds.size() <= 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("roleIds", roleIds);
        return adminRoleMapper.findByRoleIds(map);
    }

    public List<AdminRole> getByParentId(Integer parentId) {
        return adminRoleMapper.findByParentId(parentId);
    }

    public Integer getTotal(AdminRoleRequest request) {
        AdminRole adminRoleSearch = formatModelDetail(request);
        return adminRoleMapper.findTotalByParams(adminRoleSearch);
    }

    public Integer save(AdminRoleRequest request) {
        AdminRole adminRole;
        if (request.getId() != null) {
            adminRole = adminRoleMapper.findOneById(request.getId());
        } else {
            adminRole = new AdminRole();
            adminRole.setAdminRoleCreateAt(new Date());
        }
        adminRole.setAdminRoleParentId(request.getParentId());
        adminRole.setAdminRoleName(request.getName());
        adminRole.setAdminRoleDesc(request.getDesc());
        adminRole.setAdminRoleStatus(request.getStatus());
        adminRole.setAdminRoleType(request.getType());
        adminRole.setAdminRoleCreateBy(request.getCreateBy());
        adminRole.setAdminRoleUpdateBy(request.getUpdateBy());
        if (request.getId() != null) {
            return adminRoleMapper.updateByIdAndParams(adminRole);
        } else {
            return adminRoleMapper.insertByParams(adminRole);
        }
    }

    public Integer delete(Integer id) {
        return adminRoleMapper.deleteOneById(id);
    }

    private AdminRoleResponse formatResponseDetail(AdminRole adminRole) {
        AdminRoleResponse response = new AdminRoleResponse();
        response.setId(adminRole.getAdminRoleId());
        response.setParentId(adminRole.getAdminRoleParentId());
        response.setName(adminRole.getAdminRoleName());
        response.setDesc(adminRole.getAdminRoleDesc());
        response.setStatus(adminRole.getAdminRoleStatus());
        response.setType(adminRole.getAdminRoleType());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminRole.getAdminRoleCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminRole.getAdminRoleUpdateAt()));
        response.setCreateBy(adminRole.getAdminRoleCreateBy());
        response.setUpdateBy(adminRole.getAdminRoleUpdateBy());
        return response;
    }

    private AdminRole formatModelDetail(AdminRoleRequest request) {
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminRoleId(request.getId());
        adminRole.setAdminRoleParentId(request.getParentId());
        adminRole.setAdminRoleName(request.getName());
        adminRole.setAdminRoleDesc(request.getDesc());
        adminRole.setAdminRoleStatus(request.getStatus());
        adminRole.setAdminRoleType(request.getType());
        adminRole.setAdminRoleCreateAt(request.getCreateAt());
        adminRole.setAdminRoleUpdateAt(request.getUpdateAt());
        adminRole.setAdminRoleCreateBy(request.getCreateBy());
        adminRole.setAdminRoleUpdateBy(request.getUpdateBy());
        return adminRole;
    }
}