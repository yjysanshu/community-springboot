package com.lxl.common.models;

import java.util.Date;

public class RepairOrder {
    private Integer repairOrderId;
    private String repairOrderCode;
    private Integer repairOrderRoomId;
    private Integer repairOrderRepairRangeId;
    private String repairOrderPhone;
    private Integer repairOrderStatus;
    private Integer repairOrderAdminUserId;
    private String repairOrderDescription;
    private Date repairOrderCreateAt;
    private Date repairOrderUpdateAt;

    public Integer getRepairOrderId() {
        return repairOrderId;
    }

    public void setRepairOrderId(Integer repairOrderId) {
        this.repairOrderId = repairOrderId;
    }

    public String getRepairOrderCode() {
        return repairOrderCode;
    }

    public void setRepairOrderCode(String repairOrderCode) {
        this.repairOrderCode = repairOrderCode;
    }

    public Integer getRepairOrderRoomId() {
        return repairOrderRoomId;
    }

    public void setRepairOrderRoomId(Integer repairOrderRoomId) {
        this.repairOrderRoomId = repairOrderRoomId;
    }

    public Integer getRepairOrderRepairRangeId() {
        return repairOrderRepairRangeId;
    }

    public void setRepairOrderRepairRangeId(Integer repairOrderRepairRangeId) {
        this.repairOrderRepairRangeId = repairOrderRepairRangeId;
    }

    public String getRepairOrderPhone() {
        return repairOrderPhone;
    }

    public void setRepairOrderPhone(String repairOrderPhone) {
        this.repairOrderPhone = repairOrderPhone;
    }

    public Integer getRepairOrderStatus() {
        return repairOrderStatus;
    }

    public void setRepairOrderStatus(Integer repairOrderStatus) {
        this.repairOrderStatus = repairOrderStatus;
    }

    public Integer getRepairOrderAdminUserId() {
        return repairOrderAdminUserId;
    }

    public void setRepairOrderAdminUserId(Integer repairOrderAdminUserId) {
        this.repairOrderAdminUserId = repairOrderAdminUserId;
    }

    public String getRepairOrderDescription() {
        return repairOrderDescription;
    }

    public void setRepairOrderDescription(String repairOrderDescription) {
        this.repairOrderDescription = repairOrderDescription;
    }

    public Date getRepairOrderCreateAt() {
        return repairOrderCreateAt;
    }

    public void setRepairOrderCreateAt(Date repairOrderCreateAt) {
        this.repairOrderCreateAt = repairOrderCreateAt;
    }

    public Date getRepairOrderUpdateAt() {
        return repairOrderUpdateAt;
    }

    public void setRepairOrderUpdateAt(Date repairOrderUpdateAt) {
        this.repairOrderUpdateAt = repairOrderUpdateAt;
    }
}
