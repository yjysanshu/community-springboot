package com.lxl.generator.controller;

import com.lxl.common.util.FormatUtil;
import com.lxl.generator.models.Table;
import com.lxl.generator.models.request.TableRequest;
import com.lxl.generator.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @RequestMapping("/list")
    public Map getTable(@RequestBody TableRequest request) {
        List<Table> list = tableService.getTableInfo(request.getTableName());
        return FormatUtil.success(FormatUtil.formatList(list, list.size()));
    }
}
