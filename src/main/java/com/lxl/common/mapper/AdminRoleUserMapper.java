package com.lxl.common.mapper;

import com.lxl.common.models.AdminRoleUser;

import java.util.List;

public interface AdminRoleUserMapper extends BaseMapper<AdminRoleUser> {
    List<AdminRoleUser> findRolesByUserId(Integer userId);
}