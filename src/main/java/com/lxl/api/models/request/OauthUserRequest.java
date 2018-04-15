package com.lxl.api.models.request;

public class OauthUserRequest {
	private Integer id;
	private String phone;
	private String name;
	private String password;
	private String token;
	private Integer houseHoldId;
	private Integer status;

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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getHouseHoldId() {
		return this.houseHoldId;
	}

	public void setHouseHoldId(Integer houseHoldId) {
		this.houseHoldId = houseHoldId;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OauthUserRequest{" +
				"id=" + id +
				", phone='" + phone + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", token='" + token + '\'' +
				", houseHoldId=" + houseHoldId +
				", status=" + status +
				'}';
	}
}