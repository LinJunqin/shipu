package com.shipu.model;

import java.io.Serializable;

public class Love implements Serializable{

	/**
	 * 点赞表实体
	 */
	private static final long serialVersionUID = 1L;
    private Integer loveId;
    private Integer userId;
    private Integer recipeId;
    public Love(){
    	
    }
	public Love(Integer userId, Integer recipeId) {
		super();
		this.userId = userId;
		this.recipeId = recipeId;
	}

	public Integer getLoveId() {
		return loveId;
	}
	public void setLoveId(Integer loveId) {
		this.loveId = loveId;
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
}
