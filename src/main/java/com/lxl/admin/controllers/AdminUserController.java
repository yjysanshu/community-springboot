package com.lxl.admin.controllers;

import com.lxl.admin.models.Menu;
import com.lxl.admin.models.request.AdminUserRequest;
import com.lxl.admin.models.request.ChangeRequest;
import com.lxl.admin.models.response.AdminUserResponse;
import com.lxl.common.services.AdminUserService;
import com.lxl.common.services.MenuService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController extends CommonController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/login")
    public Map login() throws Exception {
        throw new Exception("xxxxxxxxx");
    }

    @RequestMapping(value = "/list")
    public Map list(AdminUserRequest request) {
        return FormatUtil.success(FormatUtil.formatList(adminUserService.getList(request),
                adminUserService.getTotal(request)));
    }

    @RequestMapping("/save")
    public Map save(@RequestBody AdminUserRequest request) {
        return FormatUtil.success(adminUserService.save(request));
    }

    @RequestMapping("/reset-pwd")
    public Map resetPwd() {
        return FormatUtil.success(adminUserService.resetPwd());
    }

    @RequestMapping("/change-pwd")
    public Map changePwd(@RequestBody ChangeRequest request) {
        int count = adminUserService.changePwd(request);
        return FormatUtil.success(count);
    }

    @RequestMapping(value = "/menu")
    public Map menu() throws IOException {
        List<Menu> list = menuService.getMenuByUser();
        return FormatUtil.success(list);
    }

    @RequestMapping(value = "/info")
    public Map info() {
        return FormatUtil.success();
    }

    @RequestMapping("/logout")
    public Map logout() {
        return FormatUtil.success();
    }
}
