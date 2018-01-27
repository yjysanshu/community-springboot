package com.lxl.common.models;

import java.util.Date;

public class Advert {
    private Integer advertId;
    private String advertDepartment;
    private String advertTitle;
    private String advertPic;
    private Integer advertType;
    private Integer advertSort;
    private Integer advertIsTop;
    private String advertContent;
    private Date advertCreateAt;
    private Date advertUpdateAt;

    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
    }

    public String getAdvertDepartment() {
        return advertDepartment;
    }

    public void setAdvertDepartment(String advertDepartment) {
        this.advertDepartment = advertDepartment;
    }

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }

    public String getAdvertPic() {
        return advertPic;
    }

    public void setAdvertPic(String advertPic) {
        this.advertPic = advertPic;
    }

    public Integer getAdvertType() {
        return advertType;
    }

    public void setAdvertType(Integer advertType) {
        this.advertType = advertType;
    }

    public Integer getAdvertSort() {
        return advertSort;
    }

    public void setAdvertSort(Integer advertSort) {
        this.advertSort = advertSort;
    }

    public Integer getAdvertIsTop() {
        return advertIsTop;
    }

    public void setAdvertIsTop(Integer advertIsTop) {
        this.advertIsTop = advertIsTop;
    }

    public String getAdvertContent() {
        return advertContent;
    }

    public void setAdvertContent(String advertContent) {
        this.advertContent = advertContent;
    }

    public Date getAdvertCreateAt() {
        return advertCreateAt;
    }

    public void setAdvertCreateAt(Date advertCreateAt) {
        this.advertCreateAt = advertCreateAt;
    }

    public Date getAdvertUpdateAt() {
        return advertUpdateAt;
    }

    public void setAdvertUpdateAt(Date advertUpdateAt) {
        this.advertUpdateAt = advertUpdateAt;
    }
}
