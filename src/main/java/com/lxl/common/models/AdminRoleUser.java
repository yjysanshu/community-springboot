package com.lxl.common.models;

import java.util.Date;

public class AdminRoleUser {
	private Integer adminRoleUserId;
	private Integer adminRoleUserAdminRoleId;
	private Integer adminRoleUserAdminUserId;
	private Date adminRoleUserCreateAt;
	private Date adminRoleUserUpdateAt;
	private String adminRoleUserCreateBy;
	private String adminRoleUserUpdateBy;

	public Integer getAdminRoleUserId() {
		return this.adminRoleUserId;
	}

	public void setAdminRoleUserId(Integer adminRoleUserId) {
		this.adminRoleUserId = adminRoleUserId;
	}

	public Integer getAdminRoleUserAdminRoleId() {
		return this.adminRoleUserAdminRoleId;
	}

	public void setAdminRoleUserAdminRoleId(Integer adminRoleUserAdminRoleId) {
		this.adminRoleUserAdminRoleId = adminRoleUserAdminRoleId;
	}

	public Integer getAdminRoleUserAdminUserId() {
		return this.adminRoleUserAdminUserId;
	}

	public void setAdminRoleUserAdminUserId(Integer adminRoleUserAdminUserId) {
		this.adminRoleUserAdminUserId = adminRoleUserAdminUserId;
	}

	public Date getAdminRoleUserCreateAt() {
		return this.adminRoleUserCreateAt;
	}

	public void setAdminRoleUserCreateAt(Date adminRoleUserCreateAt) {
		this.adminRoleUserCreateAt = adminRoleUserCreateAt;
	}

	public Date getAdminRoleUserUpdateAt() {
		return this.adminRoleUserUpdateAt;
	}

	public void setAdminRoleUserUpdateAt(Date adminRoleUserUpdateAt) {
		this.adminRoleUserUpdateAt = adminRoleUserUpdateAt;
	}

	public String getAdminRoleUserCreateBy() {
		return this.adminRoleUserCreateBy;
	}

	public void setAdminRoleUserCreateBy(String adminRoleUserCreateBy) {
		this.adminRoleUserCreateBy = adminRoleUserCreateBy;
	}

	public String getAdminRoleUserUpdateBy() {
		return this.adminRoleUserUpdateBy;
	}

	public void setAdminRoleUserUpdateBy(String adminRoleUserUpdateBy) {
		this.adminRoleUserUpdateBy = adminRoleUserUpdateBy;
	}
}