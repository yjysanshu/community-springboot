package com.lxl.common.services;

import com.lxl.admin.models.request.PaymentRequest;
import com.lxl.admin.models.response.PaymentResponse;
import com.lxl.common.mapper.PaymentMapper;
import com.lxl.common.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    public List<PaymentResponse> getList(PaymentRequest request) {
        Payment paymentSearch = formatModelDetail(request);
        List<Payment> listPayment = paymentMapper.findByParams(paymentSearch);
        List<PaymentResponse> list = new ArrayList<>();
        for (Payment payment : listPayment) {
            PaymentResponse paymentResponse = formatResponseDetail(payment);
            list.add(paymentResponse);
        }
        return list;
    }

    public Integer getTotal(PaymentRequest request) {
        Payment paymentSearch = formatModelDetail(request);
        return paymentMapper.findTotalByParams(paymentSearch);
    }

    public Integer save(PaymentRequest request) {
        Payment payment;
        if (request.getId() != null) {
            payment = paymentMapper.findOneById(request.getId());
        } else {
            payment = new Payment();
            payment.setPaymentCreateAt(new Date());
        }
        payment.setPaymentCreateAt(request.getCreateAt());
        if (request.getId() != null) {
            return paymentMapper.updateByIdAndParams(payment);
        } else {
            return paymentMapper.insertByParams(payment);
        }
    }

    private PaymentResponse formatResponseDetail(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setId(payment.getPaymentId());
        response.setType(payment.getPaymentType());
        response.setCreateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(payment.getPaymentCreateAt()));
        response.setUpdateAt((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(payment.getPaymentUpdateAt()));
        return response;
    }

    private Payment formatModelDetail(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setPaymentId(request.getId());
        payment.setPaymentCreateAt(request.getCreateAt());
        payment.setPaymentUpdateAt(request.getUpdateAt());
        return payment;
    }
}
