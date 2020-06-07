package com.spring.herethere.vo;

import java.util.Date;

public class MemberVo {
	private String userId;
	private String userPass;
	private String userName;
	private String userEmail;
	private Date joinDate;
	private Integer adminCode;

	public MemberVo() {
	}

	public MemberVo(String userId, String userPass, String userName, String userEmail, Date joinDate, Integer adminCode) {
		this.userId = userId;
		this.userPass = userPass;
		this.userName = userName;
		this.userEmail = userEmail;
		this.joinDate = joinDate;
		this.adminCode = adminCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Integer getAdminCode() {
		return adminCode;
	}

	public void setAdminCode(Integer adminCode) {
		this.adminCode = adminCode;
	}

	@Override
	public String toString() {
		return "MemberVo [userId=" + userId + ", userPass=" + userPass + ", userName=" + userName + ", userEmail=" + userEmail + ", joinDate="
				+ joinDate + ", adminCode=" + adminCode + "]";
	}

}
