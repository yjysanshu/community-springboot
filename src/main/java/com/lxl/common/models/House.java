package com.lxl.common.models;

import java.util.Date;

public class House {
    private Integer houseId;
    private String houseCode;
    private String houseName;
    private Integer houseStages;
    private String houseDescription;
    private Date houseCreateAt;
    private Date houseUpdateAt;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Integer getHouseStages() {
        return houseStages;
    }

    public void setHouseStages(Integer houseStages) {
        this.houseStages = houseStages;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }

    public Date getHouseCreateAt() {
        return houseCreateAt;
    }

    public void setHouseCreateAt(Date houseCreateAt) {
        this.houseCreateAt = houseCreateAt;
    }

    public Date getHouseUpdateAt() {
        return houseUpdateAt;
    }

    public void setHouseUpdateAt(Date houseUpdateAt) {
        this.houseUpdateAt = houseUpdateAt;
    }
}
