package com.lxl.admin.controllers;

import com.lxl.admin.resolve.Exception1;
import com.lxl.admin.resolve.ExceptionResolver;
import com.lxl.common.consts.ErrorConst;
import com.lxl.common.services.MenuService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/login")
    public Map login() throws Exception {
        throw new Exception("xxxxxxxxx");
        //return FormatUtil.fail();
    }

    @RequestMapping(value = "/menu")
    public Map menu() throws Exception1 {
        throw new Exception1();
        //List<Menu> list = menuService.getMenuByUser();
        //return FormatUtil.success(list);
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
