package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.TeachMapper;
import com.shipu.model.Teach;
import com.shipu.service.TeachService;
@Service
@Transactional
public class TeachServiceImpl implements TeachService{
	@Autowired
    private TeachMapper teachMapper;
	public boolean addTeach(Teach teach) {	
		return teachMapper.addTeach(teach);
	}

	public boolean deleteTeach(Teach teach) {
		return teachMapper.deleteTeach(teach);
	}

	public List<Teach> findStudentsById(Integer teacherId) {
		return teachMapper.findStudentsById(teacherId);
	}

	public List<Teach> findTeachersById(Integer studentId) {
		return teachMapper.findTeachersById(studentId);
	}

	public Teach findTeachById(Teach teach) {
		
		return teachMapper.findTeachById(teach);
	}
 
}
