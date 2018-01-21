package com.lxl.admin.controllers;

import com.lxl.common.util.FormatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminUserController {

    @RequestMapping
    public Map login() {
        return FormatUtil.success(null);
    }
}
