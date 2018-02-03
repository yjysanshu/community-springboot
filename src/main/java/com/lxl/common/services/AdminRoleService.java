package com.lxl.common.services;

import com.lxl.admin.models.request.AdminRoleRequest;
import com.lxl.admin.models.response.AdminRoleResponse;
import com.lxl.common.mapper.AdminRoleMapper;
import com.lxl.common.models.AdminRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

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