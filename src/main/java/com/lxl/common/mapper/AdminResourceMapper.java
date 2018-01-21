package com.lxl.common.mapper;

import com.lxl.common.models.AdminResource;

import java.util.List;

public interface AdminResourceMapper extends BaseMapper<AdminResource> {
    List<AdminResource> findByParentId(Integer parentId);
}
