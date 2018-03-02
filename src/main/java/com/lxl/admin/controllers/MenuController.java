package com.lxl.admin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lxl.admin.models.Menu;
import com.lxl.common.services.MenuService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public Map list(@RequestBody Menu menu) throws IOException {
        return FormatUtil.success(menuService.getList(menu));
    }

    @RequestMapping("/father-menus")
    public Map fatherMenus() throws IOException {
        return FormatUtil.success(menuService.getFatherList());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map save(@RequestBody Menu request) throws JsonProcessingException {
        return FormatUtil.success(menuService.save(request));
    }
}
