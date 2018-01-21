package com.lxl.admin.controllers;

import com.lxl.admin.models.Menu;
import com.lxl.common.services.MenuService;
import com.lxl.common.util.FormatUtil;
import com.lxl.mock.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AdminUserController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/admin-user/login")
    public Map login() {
        return FormatUtil.success(null);
    }

    @RequestMapping(value = "/admin-user/menu")
    public Map menu() throws IOException {
        List<Menu> list = menuService.getUserMenu(0);
        return FormatUtil.success(list);
    }

    @RequestMapping(value = "/admin-user/info")
    public Map info() {
        return FormatUtil.success(null);
    }
}
