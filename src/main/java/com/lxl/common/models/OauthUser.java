package com.lxl.common.models;

import java.util.Date;

public class OauthUser {
	private Integer oauthUserId;
	private String oauthUserPhone;
	private String oauthUserName;
	private String oauthUserPasswordHash;
	private String oauthUserPasswordResetToken;
	private Integer oauthUserHouseHoldId;
	private Integer oauthUserStatus;
	private Date oauthUserCreateAt;
	private Date oauthUserUpdateAt;

	public Integer getOauthUserId() {
		return this.oauthUserId;
	}

	public void setOauthUserId(Integer oauthUserId) {
		this.oauthUserId = oauthUserId;
	}

	public String getOauthUserPhone() {
		return this.oauthUserPhone;
	}

	public void setOauthUserPhone(String oauthUserPhone) {
		this.oauthUserPhone = oauthUserPhone;
	}

	public String getOauthUserName() {
		return this.oauthUserName;
	}

	public void setOauthUserName(String oauthUserName) {
		this.oauthUserName = oauthUserName;
	}

	public String getOauthUserPasswordHash() {
		return this.oauthUserPasswordHash;
	}

	public void setOauthUserPasswordHash(String oauthUserPasswordHash) {
		this.oauthUserPasswordHash = oauthUserPasswordHash;
	}

	public String getOauthUserPasswordResetToken() {
		return this.oauthUserPasswordResetToken;
	}

	public void setOauthUserPasswordResetToken(String oauthUserPasswordResetToken) {
		this.oauthUserPasswordResetToken = oauthUserPasswordResetToken;
	}

	public Integer getOauthUserHouseHoldId() {
		return this.oauthUserHouseHoldId;
	}

	public void setOauthUserHouseHoldId(Integer oauthUserHouseHoldId) {
		this.oauthUserHouseHoldId = oauthUserHouseHoldId;
	}

	public Integer getOauthUserStatus() {
		return this.oauthUserStatus;
	}

	public void setOauthUserStatus(Integer oauthUserStatus) {
		this.oauthUserStatus = oauthUserStatus;
	}

	public Date getOauthUserCreateAt() {
		return this.oauthUserCreateAt;
	}

	public void setOauthUserCreateAt(Date oauthUserCreateAt) {
		this.oauthUserCreateAt = oauthUserCreateAt;
	}

	public Date getOauthUserUpdateAt() {
		return this.oauthUserUpdateAt;
	}

	public void setOauthUserUpdateAt(Date oauthUserUpdateAt) {
		this.oauthUserUpdateAt = oauthUserUpdateAt;
	}
}