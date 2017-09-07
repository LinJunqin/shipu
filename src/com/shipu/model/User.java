package com.shipu.model;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * user model
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String  phoneNum;
	private String  pwd;
	private String  avatar;
	private String  nickname;
	private String  gender;
	private String  birthday;
	private String  sessionId;
	public User(){
		
	}
	public User(Integer userId, String phoneNum, String pwd) {
		super();
		this.userId = userId;
		this.phoneNum = phoneNum;
		this.pwd = pwd;
	}
	public User(Integer userId, String phoneNum, String pwd, String avatar,
			String nickname, String gender, String birthday) {
		super();
		this.userId = userId;
		this.phoneNum = phoneNum;
		this.pwd = pwd;
		this.avatar = avatar;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
