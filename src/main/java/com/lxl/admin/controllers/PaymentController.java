package com.lxl.admin.controllers;

import com.lxl.admin.models.request.PaymentRequest;
import com.lxl.common.services.HouseService;
import com.lxl.common.services.PaymentService;
import com.lxl.common.util.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/list")
    public Map list(@RequestBody PaymentRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", paymentService.getList(request));
        map.put("total", paymentService.getTotal(request));
        return FormatUtil.success(map);
    }

    @RequestMapping("/save")
    public Map save(@RequestBody PaymentRequest request) {
        return FormatUtil.success(paymentService.save(request));
    }
}
