package com.lxl.admin.models.request;

public class RepairOrderRequest extends BaseRequest {
	private Integer id;
	private String code;
	private Integer userId;
	private Integer repairRangeId;
	private String phone;
	private Integer status;
	private Integer adminUserId;
	private String description;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRepairRangeId() {
		return this.repairRangeId;
	}

	public void setRepairRangeId(Integer repairRangeId) {
		this.repairRangeId = repairRangeId;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdminUserId() {
		return this.adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}