package com.lxl.generator.mapper;

import com.lxl.generator.models.Table;

import java.util.List;

public interface TableMapper {
    List<Table> select(String tableName);
}
