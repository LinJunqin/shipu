package com.shipu.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {

	/**
	 * 评论表实体
	 */
	private static final long serialVersionUID = 1L;
    private Integer commentId;
    private Integer userId;
    private Integer recipeId;
    private String content;
    private Integer replyerId;
    private Timestamp commentDate;
    public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	public Comment(){
    	
    }
    
	public Comment(Integer userId, Integer recipeId, String content) {
		super();
		this.userId = userId;
		this.recipeId = recipeId;
		this.content = content;
	}

	public Comment(Integer userId, Integer recipeId, String content,
			Integer replyerId) {
		super();
		this.userId = userId;
		this.recipeId = recipeId;
		this.content = content;
		this.replyerId = replyerId;
	}

	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReplyerId() {
		return replyerId;
	}

	public void setReplyerId(Integer replyerId) {
		this.replyerId = replyerId;
	}
}
