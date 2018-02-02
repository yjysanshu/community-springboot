package com.lxl.generator.controller;

import com.lxl.generator.execption.GeneratorException;
import com.lxl.generator.models.GeneratorRequest;
import com.lxl.generator.service.TableService;
import com.lxl.generator.service.generator.BaseService;
import com.lxl.generator.util.ActionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/generator")
public class GeneratorController {

    @Autowired
    private TableService tableService;

    @RequestMapping("/index")
    public String index(@RequestBody GeneratorRequest request) throws GeneratorException {
        Boolean isTrue = false;
        for (String action : request.getActions()) {
            isTrue = ActionFactory.getInstance(action).init(tableService.getTableInfo(request.getTableName()),
                    request.getPackageName(), request.getTableName()).handle();
        }
        if (isTrue) {
            return "{code: 0, message: 成功}";
        }
        return "{code: 1, message: 失败}";
    }
}
