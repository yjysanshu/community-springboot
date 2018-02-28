package com.lxl.generator.controller;

import com.lxl.common.util.FormatUtil;
import com.lxl.generator.models.request.DatabaseRequest;
import com.lxl.generator.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseService databaseService;

    @RequestMapping("/index")
    public Map index(@RequestBody DatabaseRequest request) {
        List list = databaseService.getDatabaseInfo(request.getDatabaseName());
        return FormatUtil.success(FormatUtil.formatList(list, list.size()));
    }

    @RequestMapping("/list")
    public Map list() {
        return FormatUtil.success(databaseService.getDatabases());
    }
}
