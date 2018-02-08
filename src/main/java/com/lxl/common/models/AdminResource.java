package com.lxl.common.models;

import java.util.Date;
import java.util.List;

public class AdminResource {

    private Integer adminResourceId;
    private Integer adminResourceParentId;
    private Integer adminResourceType;
    private String adminResourceTarget;
    private String adminResourceData;
    private String adminResourceUrl;
    private String adminResourceName;
    private Date adminResourceCreateAt;
    private Date adminResourceUpdateAt;
    private String adminResourceCreateBy;
    private String adminResourceUpdateBy;

    private List<AdminRole> adminRoles;

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

    public String getAdminResourceUrl() {
        return adminResourceUrl;
    }

    public void setAdminResourceUrl(String adminResourceUrl) {
        this.adminResourceUrl = adminResourceUrl;
    }

    public String getAdminResourceName() {
        return adminResourceName;
    }

    public void setAdminResourceName(String adminResourceName) {
        this.adminResourceName = adminResourceName;
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

    public List<AdminRole> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(List<AdminRole> adminRoles) {
        this.adminRoles = adminRoles;
    }

}
