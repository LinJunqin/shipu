package com.shipu.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.LevelMapper;
import com.shipu.model.Level;
import com.shipu.service.LevelService;
@Service
@Transactional
public class LevelServiceImpl implements LevelService{
	@Autowired
    private LevelMapper levelMapper;
	public boolean addLevel(Level level) {
		return levelMapper.addLevel(level);
	}

	public boolean deleteLevel(Integer userId) {
		return levelMapper.deleteLevel(userId);
	}

	public Level findLevelById(Integer userId) {
		return levelMapper.findLevelById(userId);
	}
	public boolean modifyStudentLevel(Level studentLevel) {
		return levelMapper.modifyStudentLevel(studentLevel);
		
	}
	public boolean modifyTeacherLevel(Level teacherLevel) {
		return levelMapper.modifyTeacherLevel(teacherLevel);
		
	}

	public boolean modifySumLevel(Level sumLevel) {
		
		return levelMapper.modifySumLevel(sumLevel);
	}
   
}
