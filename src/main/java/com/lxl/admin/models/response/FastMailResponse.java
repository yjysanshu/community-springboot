package com.lxl.admin.models.response;

public class FastMailResponse extends BaseResponse {
	private Integer id;
	private String orderNo;
	private String customNo;
	private String business;
	private Integer receiveRoomId;
	private String receiveName;
	private String receivePhone;
	private String receiveAddress;
	private Integer status;
	private String memo;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomNo() {
		return this.customNo;
	}

	public void setCustomNo(String customNo) {
		this.customNo = customNo;
	}

	public String getBusiness() {
		return this.business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Integer getReceiveRoomId() {
		return this.receiveRoomId;
	}

	public void setReceiveRoomId(Integer receiveRoomId) {
		this.receiveRoomId = receiveRoomId;
	}

	public String getReceiveName() {
		return this.receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getReceivePhone() {
		return this.receivePhone;
	}

	public void setReceivePhone(String receivePhone) {
		this.receivePhone = receivePhone;
	}

	public String getReceiveAddress() {
		return this.receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}