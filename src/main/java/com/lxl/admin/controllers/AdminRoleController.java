package com.lxl.admin.controllers;

import com.lxl.admin.models.request.AdminPrivilegeRequest;
import com.lxl.admin.models.request.AdminRoleRequest;
import com.lxl.admin.models.request.IdRequest;
import com.lxl.admin.models.request.PrivilegeRequest;
import com.lxl.common.services.AdminPrivilegeService;
import com.lxl.common.services.AdminRoleService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @Autowired
    private AdminPrivilegeService adminPrivilegeService;

    @RequestMapping("/list")
    public Map list(@RequestBody AdminRoleRequest request) {
        return FormatUtil.success(adminRoleService.manageRole(1));
    }

    @RequestMapping("/get-menu")
    public Map getMenu(@RequestBody IdRequest request) throws IOException {
        return FormatUtil.success(adminPrivilegeService.getRoleMenu(request.getId()));
    }

    @RequestMapping("/save")
    public Map save(@RequestBody AdminRoleRequest request) {
        return FormatUtil.success(adminRoleService.save(request));
    }

    @RequestMapping("/save-privilege")
    public Map savePrivilege(@RequestBody PrivilegeRequest request) {
        return FormatUtil.success(adminPrivilegeService.savePrivilege(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody AdminRoleRequest request) {
        if (adminRoleService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}