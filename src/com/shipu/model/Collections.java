package com.shipu.model;

import java.io.Serializable;

public class Collections implements Serializable {

	/**
	 * 收藏表
	 */
	private static final long serialVersionUID = 1L;
    private Integer collectionId;
    private Integer userId;
    private Integer recipeId;
    public Collections(){
    	
    }
	public Integer getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
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
	public Collections(Integer userId, Integer recipeId) {
		super();
		this.userId = userId;
		this.recipeId = recipeId;
	}
    
}
