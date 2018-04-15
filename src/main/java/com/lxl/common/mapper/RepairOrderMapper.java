package com.lxl.common.mapper;

import com.lxl.common.models.RepairOrder;

import java.util.List;

public interface RepairOrderMapper extends BaseMapper<RepairOrder> {
    List<RepairOrder> findByUserId(Integer userId);
}