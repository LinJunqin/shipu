package com.shipu.model;

import java.io.Serializable;

public class Follow implements Serializable{

	/**
	 * 关注表实体
	 * 多对多的关系，增加和删除时，要传整个实体
	 */
	private static final long serialVersionUID = 1L;
    private Integer followId;
    private Integer userId;
    private Integer followeeId;
    public Follow(){
    	
    }
	public Follow(Integer userId, Integer followeeId) {
		super();
		this.userId = userId;
		this.followeeId = followeeId;
	}
	public Integer getFollowId() {
		return followId;
	}
	public void setFollowId(Integer followId) {
		this.followId = followId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFolloweeId() {
		return followeeId;
	}
	public void setFolloweeId(Integer followeeId) {
		this.followeeId = followeeId;
	}
    
}
