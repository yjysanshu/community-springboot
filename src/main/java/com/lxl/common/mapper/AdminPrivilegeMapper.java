package com.lxl.common.mapper;

import com.lxl.common.models.AdminPrivilege;

import java.util.List;
import java.util.Map;

public interface AdminPrivilegeMapper extends BaseMapper<AdminPrivilege> {
    Integer findByRoleIdsAndResourceId(Map map);
    List<AdminPrivilege> findByRoleId(Integer roleId);
    Integer deleteByRoleIdAndResourceId(Map map);
}