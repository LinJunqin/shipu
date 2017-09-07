package com.shipu.model;

import java.io.Serializable;

public class Level implements Serializable{

	/**
	 * 等级表
	 */
	private static final long serialVersionUID = 1L;
	private Integer levelId;
	private Integer userId;
	private Integer sumLevel;
    private Integer studentLevel;
    private Integer teacherLevel;
    public Level(){
    	
    }
	public Level(Integer userId, Integer sumLevel, Integer studentLevel,
			Integer teacherLevel) {
		super();
		this.userId = userId;
		this.sumLevel = sumLevel;
		this.studentLevel = studentLevel;
		this.teacherLevel = teacherLevel;
	}
	public Integer getLevelId() {
		return levelId;
	}
	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSumLevel() {
		return sumLevel;
	}
	public void setSumLevel(Integer sumLevel) {
		this.sumLevel = sumLevel;
	}
	public Integer getStudentLevel() {
		return studentLevel;
	}
	public void setStudentLevel(Integer studentLevel) {
		this.studentLevel = studentLevel;
	}
	public Integer getTeacherLevel() {
		return teacherLevel;
	}
	public void setTeacherLevel(Integer teacherLevel) {
		this.teacherLevel = teacherLevel;
	}
   
    
}
