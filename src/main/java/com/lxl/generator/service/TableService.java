package com.lxl.generator.service;

import com.lxl.generator.mapper.TableMapper;
import com.lxl.generator.models.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {

    @Autowired
    private TableMapper tableMapper;

    public List<Table> getTableInfo(String tableName) {
        return tableMapper.select(tableName);
    }
}
