package com.lxl.admin.controllers;

import com.lxl.admin.models.request.FastMailRequest;
import com.lxl.common.services.FastMailService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fast-mail")
public class FastMailController {

    @Autowired
    private FastMailService fastMailService;

    @RequestMapping("/list")
    public Map list(@RequestBody FastMailRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", fastMailService.getList(request));
        map.put("total", fastMailService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody FastMailRequest request) {
        return FormatUtil.success(fastMailService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody FastMailRequest request) {
        if (fastMailService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}