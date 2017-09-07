package com.shipu.service;

import com.shipu.model.Level;

public interface LevelService {
	public boolean addLevel(Level level);
    public boolean deleteLevel(Integer userId);
    public Level findLevelById(Integer userId);
    public boolean modifyStudentLevel(Level studentLevel);
    public boolean modifyTeacherLevel(Level teacherLevel);
    public boolean modifySumLevel(Level sumLevel);
}
