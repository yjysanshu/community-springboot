package com.lxl.api.controllers;

import com.lxl.common.consts.ErrorConst;
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
    public Map index() {
        return FormatUtil.success(null);
    }

    @RequestMapping("/site/index1")
    public Map index1() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "yuanjy");
        map.put("password", "123456");
        return FormatUtil.success(null);
    }

    @RequestMapping("/site/index2")
    public Map index2() {
        return FormatUtil.fail(null);
    }

    @RequestMapping("/site/index3")
    public Map index3() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "yuanjy");
        map.put("password", "123456");
        return FormatUtil.fail(map);
    }

    @RequestMapping("/site/index4")
    public Map index4() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "yuanjy");
        map.put("password", "123456");
        return FormatUtil.fail(ErrorConst.SYSTEM_EXCEPTION, map);
    }

    @RequestMapping("/site/index5")
    public Map index5() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "yuanjy");
        map.put("password", "123456");
        return FormatUtil.fail(ErrorConst.SYSTEM_EXCEPTION, ErrorConst.messageMap.get(ErrorConst.PARAMS_EXCEPTION), map);
    }
}
