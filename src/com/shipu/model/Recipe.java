package com.shipu.model;

import java.io.Serializable;
import java.sql.Timestamp;
public class Recipe implements Serializable {

	/**
	 * 食谱实体类
	 */
	private static final long serialVersionUID = 1L;
    private Integer recipeId;
    private String name;
    private String fodder;
    private String kind;
    private String situation;
    private String difficulty;
    private String cookStep;
    private String foodPhotoPath;
    private String notification;
    private String cookVideoPath;
    private Integer userId;
    private Timestamp uploadDate;
    public Timestamp getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Integer getHotLevel() {
		return hotLevel;
	}
	public void setHotLevel(Integer hotLevel) {
		this.hotLevel = hotLevel;
	}
	private Integer hotLevel;
    public Recipe(){
    	
    }  
	public Recipe(String name, String fodder, String kind, String situation,
			String difficulty, String cookStep, Integer userId) {
		super();
		this.name = name;
		this.fodder = fodder;
		this.kind = kind;
		this.situation = situation;
		this.difficulty = difficulty;
		this.cookStep = cookStep;
		this.userId = userId;
	}

	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public String getFodder() {
		return fodder;
	}
	public void setFodder(String fodder) {
		this.fodder = fodder;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getCookStep() {
		return cookStep;
	}
	public void setCookStep(String cookStep) {
		this.cookStep = cookStep;
	}
	public String getFoodPhotoPath() {
		return foodPhotoPath;
	}
	public void setFoodPhotoPath(String foodPhotoPath) {
		this.foodPhotoPath = foodPhotoPath;
	}
	public String getNotification() {
		return notification;
	}
	public void setNotification(String notification) {
		this.notification = notification;
	}
	public String getCookVideoPath() {
		return cookVideoPath;
	}
	public void setCookVideoPath(String cookVideoPath) {
		this.cookVideoPath = cookVideoPath;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
