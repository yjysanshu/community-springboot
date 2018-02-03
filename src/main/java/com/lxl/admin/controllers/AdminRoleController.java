package com.lxl.admin.controllers;

import com.lxl.admin.models.request.AdminRoleRequest;
import com.lxl.common.services.AdminRoleService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminRole")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping("/list")
    public Map list(@RequestBody AdminRoleRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", adminRoleService.getList(request));
        map.put("total", adminRoleService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody AdminRoleRequest request) {
        return FormatUtil.success(adminRoleService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(Integer id) {
        return FormatUtil.success(adminRoleService.delete(id));
    }
}