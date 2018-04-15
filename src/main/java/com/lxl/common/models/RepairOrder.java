package com.lxl.common.models;

import java.util.Date;

public class RepairOrder {
	private Integer repairOrderId;
	private String repairOrderCode;
	private Integer repairOrderUserId;
	private Integer repairOrderRepairRangeId;
	private String repairOrderPhone;
	private Integer repairOrderStatus;
	private Integer repairOrderAdminUserId;
	private String repairOrderDescription;
	private Date repairOrderCreateAt;
	private Date repairOrderUpdateAt;

	public Integer getRepairOrderId() {
		return this.repairOrderId;
	}

	public void setRepairOrderId(Integer repairOrderId) {
		this.repairOrderId = repairOrderId;
	}

	public String getRepairOrderCode() {
		return this.repairOrderCode;
	}

	public void setRepairOrderCode(String repairOrderCode) {
		this.repairOrderCode = repairOrderCode;
	}

	public Integer getRepairOrderUserId() {
		return this.repairOrderUserId;
	}

	public void setRepairOrderUserId(Integer repairOrderUserId) {
		this.repairOrderUserId = repairOrderUserId;
	}

	public Integer getRepairOrderRepairRangeId() {
		return this.repairOrderRepairRangeId;
	}

	public void setRepairOrderRepairRangeId(Integer repairOrderRepairRangeId) {
		this.repairOrderRepairRangeId = repairOrderRepairRangeId;
	}

	public String getRepairOrderPhone() {
		return this.repairOrderPhone;
	}

	public void setRepairOrderPhone(String repairOrderPhone) {
		this.repairOrderPhone = repairOrderPhone;
	}

	public Integer getRepairOrderStatus() {
		return this.repairOrderStatus;
	}

	public void setRepairOrderStatus(Integer repairOrderStatus) {
		this.repairOrderStatus = repairOrderStatus;
	}

	public Integer getRepairOrderAdminUserId() {
		return this.repairOrderAdminUserId;
	}

	public void setRepairOrderAdminUserId(Integer repairOrderAdminUserId) {
		this.repairOrderAdminUserId = repairOrderAdminUserId;
	}

	public String getRepairOrderDescription() {
		return this.repairOrderDescription;
	}

	public void setRepairOrderDescription(String repairOrderDescription) {
		this.repairOrderDescription = repairOrderDescription;
	}

	public Date getRepairOrderCreateAt() {
		return this.repairOrderCreateAt;
	}

	public void setRepairOrderCreateAt(Date repairOrderCreateAt) {
		this.repairOrderCreateAt = repairOrderCreateAt;
	}

	public Date getRepairOrderUpdateAt() {
		return this.repairOrderUpdateAt;
	}

	public void setRepairOrderUpdateAt(Date repairOrderUpdateAt) {
		this.repairOrderUpdateAt = repairOrderUpdateAt;
	}
}