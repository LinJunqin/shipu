package com.shipu.model;

import java.io.Serializable;

public class Teach implements Serializable{

	/**
	 * 师徒表
	 */
	private static final long serialVersionUID = 1L;
    private Integer teachId;
    private Integer studentId;
    private Integer teacherId;
    
    public Teach(){
    	
    }
    public Teach(Integer studentId, Integer teacherId
			) {
		super();
		this.studentId = studentId;
		this.teacherId = teacherId;
		
	}
	public Integer getTeachId() {
		return teachId;
	}
	public void setTeachId(Integer teachId) {
		this.teachId = teachId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	
}
