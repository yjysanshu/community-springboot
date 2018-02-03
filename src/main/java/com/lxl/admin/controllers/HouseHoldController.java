package com.lxl.admin.controllers;

import com.lxl.admin.models.request.HouseHoldRequest;
import com.lxl.common.services.HouseHoldService;
import com.lxl.common.services.HouseService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/house-hold")
public class HouseHoldController {

    @Autowired
    private HouseHoldService houseHoldService;

    @RequestMapping("/list")
    public Map list(@RequestBody HouseHoldRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", houseHoldService.getList(request));
        map.put("total", houseHoldService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody HouseHoldRequest request) {
        return FormatUtil.success(houseHoldService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody HouseHoldRequest request) {
        if (houseHoldService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}
