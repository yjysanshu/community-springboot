package com.lxl.admin.models.request;

public class AdminPrivilegeRequest extends BaseRequest {
	private Integer id;
	private Integer adminResourceId;
	private Integer adminRoleId;
	private String type;
	private String createBy;
	private String updateBy;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminResourceId() {
		return this.adminResourceId;
	}

	public void setAdminResourceId(Integer adminResourceId) {
		this.adminResourceId = adminResourceId;
	}

	public Integer getAdminRoleId() {
		return this.adminRoleId;
	}

	public void setAdminRoleId(Integer adminRoleId) {
		this.adminRoleId = adminRoleId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}