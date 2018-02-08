package com.lxl.admin.controllers;

import com.lxl.admin.models.request.AdminPrivilegeRequest;
import com.lxl.common.services.AdminPrivilegeService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminPrivilege")
public class AdminPrivilegeController {

    @Autowired
    private AdminPrivilegeService adminPrivilegeService;

    @RequestMapping("/list")
    public Map list(@RequestBody AdminPrivilegeRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", adminPrivilegeService.getList(request));
        map.put("total", adminPrivilegeService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody AdminPrivilegeRequest request) {
        return FormatUtil.success(adminPrivilegeService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody AdminPrivilegeRequest request) {
        if (adminPrivilegeService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}