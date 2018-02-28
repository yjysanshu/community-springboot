package com.lxl.generator.mapper;

import com.lxl.generator.models.Database;
import com.lxl.generator.models.Databases;

import java.util.List;

public interface DatabaseMapper {
    List<Database> select(String databaseName);
    List<Databases> showTables();
}
