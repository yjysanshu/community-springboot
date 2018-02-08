package com.lxl.admin.controllers;

import com.lxl.admin.models.request.AdminRoleUserRequest;
import com.lxl.common.services.AdminRoleUserService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/adminRoleUser")
public class AdminRoleUserController {

    @Autowired
    private AdminRoleUserService adminRoleUserService;

    @RequestMapping("/list")
    public Map list(@RequestBody AdminRoleUserRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", adminRoleUserService.getList(request));
        map.put("total", adminRoleUserService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody AdminRoleUserRequest request) {
        return FormatUtil.success(adminRoleUserService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody AdminRoleUserRequest request) {
        if (adminRoleUserService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}