package com.lxl.common.mapper;

import com.lxl.common.models.AdminUser;

import java.util.List;

public interface AdminUserMapper extends BaseMapper<AdminUser> {
    AdminUser findOneByPhone(String phone);
    AdminUser findOneByEmail(String email);
    AdminUser findOneByToken(String token);
    List<AdminUser> findUserByRoleId(Integer roleId);
}