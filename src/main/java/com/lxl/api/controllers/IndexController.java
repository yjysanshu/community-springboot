package com.lxl.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/16.
 */
@RestController
public class IndexController {

    @RequestMapping("/site/index")
    public String index() {
        return "{\"code\": \"0\"}";
    }
}
