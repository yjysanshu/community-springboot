package com.lxl.common.mapper;

import com.lxl.common.models.AdminRole;

import java.util.List;
import java.util.Map;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    List<AdminRole> findRolesByParentId(Integer parentId);
    List<AdminRole> findByRoleIds(Map map);
    List<AdminRole> findByParentId(Integer parentId);
}