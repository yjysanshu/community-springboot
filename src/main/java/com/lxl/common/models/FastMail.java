package com.lxl.common.models;

import java.util.Date;

public class FastMail {
	private Integer fastMailId;
	private String fastMailOrderNo;
	private String fastMailCustomNo;
	private String fastMailBusiness;
	private Integer fastMailReceiveRoomId;
	private String fastMailReceiveName;
	private String fastMailReceivePhone;
	private String fastMailReceiveAddress;
	private Integer fastMailStatus;
	private String fastMailMemo;
	private Date fastMailCreateAt;
	private Date fastMailUpdateAt;

	public Integer getFastMailId() {
		return this.fastMailId;
	}

	public void setFastMailId(Integer fastMailId) {
		this.fastMailId = fastMailId;
	}

	public String getFastMailOrderNo() {
		return this.fastMailOrderNo;
	}

	public void setFastMailOrderNo(String fastMailOrderNo) {
		this.fastMailOrderNo = fastMailOrderNo;
	}

	public String getFastMailCustomNo() {
		return this.fastMailCustomNo;
	}

	public void setFastMailCustomNo(String fastMailCustomNo) {
		this.fastMailCustomNo = fastMailCustomNo;
	}

	public String getFastMailBusiness() {
		return this.fastMailBusiness;
	}

	public void setFastMailBusiness(String fastMailBusiness) {
		this.fastMailBusiness = fastMailBusiness;
	}

	public Integer getFastMailReceiveRoomId() {
		return this.fastMailReceiveRoomId;
	}

	public void setFastMailReceiveRoomId(Integer fastMailReceiveRoomId) {
		this.fastMailReceiveRoomId = fastMailReceiveRoomId;
	}

	public String getFastMailReceiveName() {
		return this.fastMailReceiveName;
	}

	public void setFastMailReceiveName(String fastMailReceiveName) {
		this.fastMailReceiveName = fastMailReceiveName;
	}

	public String getFastMailReceivePhone() {
		return this.fastMailReceivePhone;
	}

	public void setFastMailReceivePhone(String fastMailReceivePhone) {
		this.fastMailReceivePhone = fastMailReceivePhone;
	}

	public String getFastMailReceiveAddress() {
		return this.fastMailReceiveAddress;
	}

	public void setFastMailReceiveAddress(String fastMailReceiveAddress) {
		this.fastMailReceiveAddress = fastMailReceiveAddress;
	}

	public Integer getFastMailStatus() {
		return this.fastMailStatus;
	}

	public void setFastMailStatus(Integer fastMailStatus) {
		this.fastMailStatus = fastMailStatus;
	}

	public String getFastMailMemo() {
		return this.fastMailMemo;
	}

	public void setFastMailMemo(String fastMailMemo) {
		this.fastMailMemo = fastMailMemo;
	}

	public Date getFastMailCreateAt() {
		return this.fastMailCreateAt;
	}

	public void setFastMailCreateAt(Date fastMailCreateAt) {
		this.fastMailCreateAt = fastMailCreateAt;
	}

	public Date getFastMailUpdateAt() {
		return this.fastMailUpdateAt;
	}

	public void setFastMailUpdateAt(Date fastMailUpdateAt) {
		this.fastMailUpdateAt = fastMailUpdateAt;
	}
}