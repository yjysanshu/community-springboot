package com.lxl.admin.controllers;

import com.lxl.admin.models.request.HouseRequest;
import com.lxl.common.services.HouseService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/list")
    public Map list(@RequestBody HouseRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", houseService.getList(request));
        map.put("total", houseService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/all")
    public Map all() {
        return FormatUtil.success(houseService.getAll());
    }

    @RequestMapping("/save")
    public Map save(@RequestBody HouseRequest request) {
        return FormatUtil.success(houseService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody HouseRequest request) {
        if (houseService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}