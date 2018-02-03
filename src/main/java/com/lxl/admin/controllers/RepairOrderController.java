package com.lxl.admin.controllers;

import com.lxl.admin.models.request.RepairOrderRequest;
import com.lxl.common.services.RepairOrderService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/repair-order")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;

    @RequestMapping("/list")
    public Map list(@RequestBody RepairOrderRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", repairOrderService.getList(request));
        map.put("total", repairOrderService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody RepairOrderRequest request) {
        return FormatUtil.success(repairOrderService.save(request));
    }

    @RequestMapping("/del")
    public Map delete(@RequestBody RepairOrderRequest request) {
        if (repairOrderService.delete(request.getId()) > 0) {
            return FormatUtil.success();
        }
        return FormatUtil.fail();
    }
}