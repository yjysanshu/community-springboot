package com.lxl.common.mapper;

import com.lxl.common.models.AdminRoleUser;
import com.lxl.common.models.relevant.AdminRoleUserRelevant;

import java.util.List;
import java.util.Map;

public interface AdminRoleUserMapper extends BaseMapper<AdminRoleUser> {
    List<AdminRoleUser> findRolesByUserId(Integer userId);
    AdminRoleUser findByUserIdAndRoleId(Map map);
    AdminRoleUserRelevant findByUserId(Integer userId);
    void callProAddUserRole(Map map);
    AdminRoleUserRelevant findByRoleId(Integer roleId);
}