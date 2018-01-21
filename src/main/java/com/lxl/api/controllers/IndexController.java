package com.lxl.api.controllers;

import com.lxl.common.util.FormatUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/16.
 */
@RestController
public class IndexController {

    @RequestMapping("/site/index")
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        map.put("is_login", true);
        return FormatUtil.success(map);
    }
}
