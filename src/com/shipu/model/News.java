package com.shipu.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class News implements Serializable {

	/**
	 * 消息表
	 */
	private static final long serialVersionUID = 1L;
    private Integer newsId;
    private Integer ownerId;
    private Integer senderId;
    private String content;
    private Timestamp createDate;
    private Integer newsType;
    private Boolean isRead;
    public News(){
    	
    }
	public News(Integer ownerId, Integer senderId, String content,
			Timestamp createDate, Integer newsType, Boolean isRead) {
		super();
		this.ownerId = ownerId;
		this.senderId = senderId;
		this.content = content;
		this.createDate = createDate;
		this.newsType = newsType;
		this.isRead = isRead;
	}
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public Integer getSenderId() {
		return senderId;
	}
	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Integer getNewsType() {
		return newsType;
	}
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
    
}
