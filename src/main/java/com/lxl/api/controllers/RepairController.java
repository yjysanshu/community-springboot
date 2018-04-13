package com.lxl.api.controllers;

import com.lxl.api.models.request.RepairRequest;
import com.lxl.common.services.RepairOrderService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("apiRepairController")
@RequestMapping("/api/repair")
public class RepairController {

    @Autowired
    private RepairOrderService repairOrderService;

    @RequestMapping("/report")
    public Map report(@RequestBody RepairRequest request) {
        Integer result = repairOrderService.userReport(request);
        if (result > 0) {
            return FormatUtil.success();
        } else {
            return FormatUtil.fail();
        }
        //return FormatUtil.success();
    }
}
