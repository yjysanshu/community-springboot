package com.lxl.api.controllers;

import com.lxl.common.services.FastMailService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("apiFastMail")
@RequestMapping("/api/fast-mail")
public class FastMailController {

    @Autowired
    private FastMailService fastMailService;

    @RequestMapping("/list")
    public Map getList() {
        return FormatUtil.success(fastMailService.getListByUser());
    }
}
