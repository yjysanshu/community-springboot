package com.lxl.common.models;

import java.util.Date;

public class Payment {
    private Integer paymentId;
    private String paymentSerialNo;
    private String paymentCardNo;
    private Integer paymentType;
    private Integer paymentOrderId;
    private Date paymentCreateAt;
    private Date paymentUpdateAt;

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentSerialNo() {
        return paymentSerialNo;
    }

    public void setPaymentSerialNo(String paymentSerialNo) {
        this.paymentSerialNo = paymentSerialNo;
    }

    public String getPaymentCardNo() {
        return paymentCardNo;
    }

    public void setPaymentCardNo(String paymentCardNo) {
        this.paymentCardNo = paymentCardNo;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentOrderId() {
        return paymentOrderId;
    }

    public void setPaymentOrderId(Integer paymentOrderId) {
        this.paymentOrderId = paymentOrderId;
    }

    public Date getPaymentCreateAt() {
        return paymentCreateAt;
    }

    public void setPaymentCreateAt(Date paymentCreateAt) {
        this.paymentCreateAt = paymentCreateAt;
    }

    public Date getPaymentUpdateAt() {
        return paymentUpdateAt;
    }

    public void setPaymentUpdateAt(Date paymentUpdateAt) {
        this.paymentUpdateAt = paymentUpdateAt;
    }
}
