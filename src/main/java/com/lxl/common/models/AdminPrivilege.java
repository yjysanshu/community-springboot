package com.lxl.common.models;

import java.util.Date;

public class AdminPrivilege {
	private Integer adminPrivilegeId;
	private Integer adminPrivilegeAdminResourceId;
	private Integer adminPrivilegeAdminRoleId;
	private String adminPrivilegeType;
	private Date adminPrivilegeCreateAt;
	private Date adminPrivilegeUpdateAt;
	private String adminPrivilegeCreateBy;
	private String adminPrivilegeUpdateBy;

	public Integer getAdminPrivilegeId() {
		return this.adminPrivilegeId;
	}

	public void setAdminPrivilegeId(Integer adminPrivilegeId) {
		this.adminPrivilegeId = adminPrivilegeId;
	}

	public Integer getAdminPrivilegeAdminResourceId() {
		return this.adminPrivilegeAdminResourceId;
	}

	public void setAdminPrivilegeAdminResourceId(Integer adminPrivilegeAdminResourceId) {
		this.adminPrivilegeAdminResourceId = adminPrivilegeAdminResourceId;
	}

	public Integer getAdminPrivilegeAdminRoleId() {
		return this.adminPrivilegeAdminRoleId;
	}

	public void setAdminPrivilegeAdminRoleId(Integer adminPrivilegeAdminRoleId) {
		this.adminPrivilegeAdminRoleId = adminPrivilegeAdminRoleId;
	}

	public String getAdminPrivilegeType() {
		return this.adminPrivilegeType;
	}

	public void setAdminPrivilegeType(String adminPrivilegeType) {
		this.adminPrivilegeType = adminPrivilegeType;
	}

	public Date getAdminPrivilegeCreateAt() {
		return this.adminPrivilegeCreateAt;
	}

	public void setAdminPrivilegeCreateAt(Date adminPrivilegeCreateAt) {
		this.adminPrivilegeCreateAt = adminPrivilegeCreateAt;
	}

	public Date getAdminPrivilegeUpdateAt() {
		return this.adminPrivilegeUpdateAt;
	}

	public void setAdminPrivilegeUpdateAt(Date adminPrivilegeUpdateAt) {
		this.adminPrivilegeUpdateAt = adminPrivilegeUpdateAt;
	}

	public String getAdminPrivilegeCreateBy() {
		return this.adminPrivilegeCreateBy;
	}

	public void setAdminPrivilegeCreateBy(String adminPrivilegeCreateBy) {
		this.adminPrivilegeCreateBy = adminPrivilegeCreateBy;
	}

	public String getAdminPrivilegeUpdateBy() {
		return this.adminPrivilegeUpdateBy;
	}

	public void setAdminPrivilegeUpdateBy(String adminPrivilegeUpdateBy) {
		this.adminPrivilegeUpdateBy = adminPrivilegeUpdateBy;
	}

	@Override
	public String toString() {
		return "AdminPrivilege{" +
				"adminPrivilegeId=" + adminPrivilegeId +
				", adminPrivilegeAdminResourceId=" + adminPrivilegeAdminResourceId +
				", adminPrivilegeAdminRoleId=" + adminPrivilegeAdminRoleId +
				", adminPrivilegeType='" + adminPrivilegeType + '\'' +
				", adminPrivilegeCreateAt=" + adminPrivilegeCreateAt +
				", adminPrivilegeUpdateAt=" + adminPrivilegeUpdateAt +
				", adminPrivilegeCreateBy='" + adminPrivilegeCreateBy + '\'' +
				", adminPrivilegeUpdateBy='" + adminPrivilegeUpdateBy + '\'' +
				'}';
	}
}