package com.lxl.common.services;

import com.lxl.admin.models.request.AdminUserRequest;
import com.lxl.admin.models.response.AdminUserResponse;
import com.lxl.common.consts.AdminUserConst;
import com.lxl.common.mapper.AdminUserMapper;
import com.lxl.common.models.AdminUser;
import com.lxl.common.util.encrypt.BASE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    public AdminUser getOneByToken(String token) {
        return adminUserMapper.findOneByToken(token);
    }

    public List<AdminUserResponse> getList(AdminUserRequest request) {
        AdminUser adminUserSearch = formatModelDetail(request);
        List<AdminUser> listAdminUser = adminUserMapper.findByParams(adminUserSearch);
        List<AdminUserResponse> list = new ArrayList<>();
        for (AdminUser adminUser : listAdminUser) {
            AdminUserResponse adminUserResponse = formatResponseDetail(adminUser);
            list.add(adminUserResponse);
        }
        return list;
    }

    public Integer getTotal(AdminUserRequest request) {
        AdminUser adminUserSearch = formatModelDetail(request);
        return adminUserMapper.findTotalByParams(adminUserSearch);
    }

    public Integer save(AdminUserRequest request) {
        AdminUser adminUser;
        if (request.getId() != null) {
            adminUser = adminUserMapper.findOneById(request.getId());
        } else {
            adminUser = new AdminUser();
            adminUser.setAdminUserCreateAt(new Date());
        }
        adminUser.setAdminUserPhone(request.getPhone());
        adminUser.setAdminUserName(request.getName());
        adminUser.setAdminUserEmail(request.getEmail());
        adminUser.setAdminUserFullName(request.getFullName());
        adminUser.setAdminUserAvatar(request.getAvatar());
        adminUser.setAdminUserPosition(request.getPosition());
        adminUser.setAdminUserAuthKey(request.getAuthKey());
        adminUser.setAdminUserPasswordHash(request.getPasswordHash());
        adminUser.setAdminUserPasswordResetToken(request.getPasswordResetToken());
        adminUser.setAdminUserStatus(request.getStatus());
        adminUser.setAdminUserCreateBy(request.getCreateBy());
        adminUser.setAdminUserUpdateBy(request.getUpdateBy());
        if (request.getId() != null) {
            return adminUserMapper.updateByIdAndParams(adminUser);
        } else {
            return adminUserMapper.insertByParams(adminUser);
        }
    }

    public Integer delete(Integer id) {
        return adminUserMapper.deleteOneById(id);
    }

    private AdminUserResponse formatResponseDetail(AdminUser adminUser) {
        AdminUserResponse response = new AdminUserResponse();
        response.setId(adminUser.getAdminUserId());
        response.setPhone(adminUser.getAdminUserPhone());
        response.setName(adminUser.getAdminUserName());
        response.setEmail(adminUser.getAdminUserEmail());
        response.setFullName(adminUser.getAdminUserFullName());
        response.setAvatar(adminUser.getAdminUserAvatar());
        response.setPosition(adminUser.getAdminUserPosition());
        response.setAuthKey(adminUser.getAdminUserAuthKey());
        response.setPasswordHash(adminUser.getAdminUserPasswordHash());
        response.setPasswordResetToken(adminUser.getAdminUserPasswordResetToken());
        response.setStatus(adminUser.getAdminUserStatus());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminUser.getAdminUserCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(adminUser.getAdminUserUpdateAt()));
        response.setCreateBy(adminUser.getAdminUserCreateBy());
        response.setUpdateBy(adminUser.getAdminUserUpdateBy());
        return response;
    }

    private AdminUser formatModelDetail(AdminUserRequest request) {
        AdminUser adminUser = new AdminUser();
        adminUser.setAdminUserId(request.getId());
        adminUser.setAdminUserPhone(request.getPhone());
        adminUser.setAdminUserName(request.getName());
        adminUser.setAdminUserEmail(request.getEmail());
        adminUser.setAdminUserFullName(request.getFullName());
        adminUser.setAdminUserAvatar(request.getAvatar());
        adminUser.setAdminUserPosition(request.getPosition());
        adminUser.setAdminUserAuthKey(request.getAuthKey());
        adminUser.setAdminUserPasswordHash(request.getPasswordHash());
        adminUser.setAdminUserPasswordResetToken(request.getPasswordResetToken());
        adminUser.setAdminUserStatus(request.getStatus());
        adminUser.setAdminUserCreateAt(request.getCreateAt());
        adminUser.setAdminUserUpdateAt(request.getUpdateAt());
        adminUser.setAdminUserCreateBy(request.getCreateBy());
        adminUser.setAdminUserUpdateBy(request.getUpdateBy());
        return adminUser;
    }
}