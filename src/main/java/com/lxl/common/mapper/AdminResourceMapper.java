package com.lxl.common.mapper;

import com.lxl.common.models.AdminResource;

import java.util.List;
import java.util.Map;

public interface AdminResourceMapper extends BaseMapper<AdminResource> {
    List<AdminResource> findByParentId(Integer parentId);
    List<AdminResource> findByJoinPrivilegeAndParams(Map map);
}
