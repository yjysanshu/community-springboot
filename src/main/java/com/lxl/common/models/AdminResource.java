package com.lxl.common.models;

import java.util.Date;

public class AdminResource {

    private Integer adminResourceId;
    private Integer adminResourceParentId;
    private Integer adminResourceType;
    private String adminResourceTarget;
    private String adminResourceData;
    private Date adminResourceCreateAt;
    private Date adminResourceUpdateAt;
    private String adminResourceCreateBy;
    private String adminResourceUpdateBy;

    public Integer getAdminResourceId() {
        return adminResourceId;
    }

    public void setAdminResourceId(Integer adminResourceId) {
        this.adminResourceId = adminResourceId;
    }

    public Integer getAdminResourceParentId() {
        return adminResourceParentId;
    }

    public void setAdminResourceParentId(Integer adminResourceParentId) {
        this.adminResourceParentId = adminResourceParentId;
    }

    public Integer getAdminResourceType() {
        return adminResourceType;
    }

    public void setAdminResourceType(Integer adminResourceType) {
        this.adminResourceType = adminResourceType;
    }

    public String getAdminResourceTarget() {
        return adminResourceTarget;
    }

    public void setAdminResourceTarget(String adminResourceTarget) {
        this.adminResourceTarget = adminResourceTarget;
    }

    public String getAdminResourceData() {
        return adminResourceData;
    }

    public void setAdminResourceData(String adminResourceData) {
        this.adminResourceData = adminResourceData;
    }

    public Date getAdminResourceCreateAt() {
        return adminResourceCreateAt;
    }

    public void setAdminResourceCreateAt(Date adminResourceCreateAt) {
        this.adminResourceCreateAt = adminResourceCreateAt;
    }

    public Date getAdminResourceUpdateAt() {
        return adminResourceUpdateAt;
    }

    public void setAdminResourceUpdateAt(Date adminResourceUpdateAt) {
        this.adminResourceUpdateAt = adminResourceUpdateAt;
    }

    public String getAdminResourceCreateBy() {
        return adminResourceCreateBy;
    }

    public void setAdminResourceCreateBy(String adminResourceCreateBy) {
        this.adminResourceCreateBy = adminResourceCreateBy;
    }

    public String getAdminResourceUpdateBy() {
        return adminResourceUpdateBy;
    }

    public void setAdminResourceUpdateBy(String adminResourceUpdateBy) {
        this.adminResourceUpdateBy = adminResourceUpdateBy;
    }
}
