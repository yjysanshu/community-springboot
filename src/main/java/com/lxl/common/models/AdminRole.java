package com.lxl.common.models;

import java.util.Date;

public class AdminRole {
	private Integer adminRoleId;
	private Integer adminRoleParentId;
	private String adminRoleName;
	private String adminRoleDesc;
	private Integer adminRoleStatus;
	private Integer adminRoleType;
	private Date adminRoleCreateAt;
	private Date adminRoleUpdateAt;
	private String adminRoleCreateBy;
	private String adminRoleUpdateBy;

	public Integer getAdminRoleId() {
		return this.adminRoleId;
	}

	public void setAdminRoleId(Integer adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	public Integer getAdminRoleParentId() {
		return this.adminRoleParentId;
	}

	public void setAdminRoleParentId(Integer adminRoleParentId) {
		this.adminRoleParentId = adminRoleParentId;
	}

	public String getAdminRoleName() {
		return this.adminRoleName;
	}

	public void setAdminRoleName(String adminRoleName) {
		this.adminRoleName = adminRoleName;
	}

	public String getAdminRoleDesc() {
		return this.adminRoleDesc;
	}

	public void setAdminRoleDesc(String adminRoleDesc) {
		this.adminRoleDesc = adminRoleDesc;
	}

	public Integer getAdminRoleStatus() {
		return this.adminRoleStatus;
	}

	public void setAdminRoleStatus(Integer adminRoleStatus) {
		this.adminRoleStatus = adminRoleStatus;
	}

	public Integer getAdminRoleType() {
		return this.adminRoleType;
	}

	public void setAdminRoleType(Integer adminRoleType) {
		this.adminRoleType = adminRoleType;
	}

	public Date getAdminRoleCreateAt() {
		return this.adminRoleCreateAt;
	}

	public void setAdminRoleCreateAt(Date adminRoleCreateAt) {
		this.adminRoleCreateAt = adminRoleCreateAt;
	}

	public Date getAdminRoleUpdateAt() {
		return this.adminRoleUpdateAt;
	}

	public void setAdminRoleUpdateAt(Date adminRoleUpdateAt) {
		this.adminRoleUpdateAt = adminRoleUpdateAt;
	}

	public String getAdminRoleCreateBy() {
		return this.adminRoleCreateBy;
	}

	public void setAdminRoleCreateBy(String adminRoleCreateBy) {
		this.adminRoleCreateBy = adminRoleCreateBy;
	}

	public String getAdminRoleUpdateBy() {
		return this.adminRoleUpdateBy;
	}

	public void setAdminRoleUpdateBy(String adminRoleUpdateBy) {
		this.adminRoleUpdateBy = adminRoleUpdateBy;
	}
}