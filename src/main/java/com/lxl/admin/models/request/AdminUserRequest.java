package com.lxl.admin.models.request;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AdminUserRequest extends BaseRequest {
	private Integer id;
	private String phone;
	private String name;
	private String email;
	private String fullName;
	private String avatar;
	private String position;
	private String authKey;
	private String passwordHash;
	private String passwordResetToken;
	private Integer status;
	private String createBy;
	private String updateBy;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAuthKey() {
		return this.authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getPasswordHash() {
		if (this.passwordHash == null) {
			return (new BCryptPasswordEncoder().encode("ellababy"));
		}
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordResetToken() {
		return this.passwordResetToken;
	}

	public void setPasswordResetToken(String passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "AdminUserRequest{" +
				"id=" + id +
				", phone='" + phone + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", fullName='" + fullName + '\'' +
				", avatar='" + avatar + '\'' +
				", position='" + position + '\'' +
				", authKey='" + authKey + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", passwordResetToken='" + passwordResetToken + '\'' +
				", status=" + status +
				", createBy='" + createBy + '\'' +
				", updateBy='" + updateBy + '\'' +
				'}';
	}
}