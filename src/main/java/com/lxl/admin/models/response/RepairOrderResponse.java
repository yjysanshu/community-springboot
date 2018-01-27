package com.lxl.admin.models.response;

public class RepairOrderResponse extends BaseResponse {
    private Integer id;
    private String code;
    private Integer roomId;
    private Integer repairRangeId;
    private String phone;
    private Integer status;
    private Integer adminUserId;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRepairRangeId() {
        return repairRangeId;
    }

    public void setRepairRangeId(Integer repairRangeId) {
        this.repairRangeId = repairRangeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
