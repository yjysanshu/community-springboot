package com.lxl.admin.controllers;

import com.lxl.admin.models.House;
import com.lxl.admin.models.request.AdvertRequest;
import com.lxl.common.services.AdvertService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @RequestMapping("/list")
    public Map list(@RequestBody AdvertRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", advertService.getList(request));
        map.put("total", advertService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody AdvertRequest request) {
        return FormatUtil.success(advertService.save(request));
    }
}
