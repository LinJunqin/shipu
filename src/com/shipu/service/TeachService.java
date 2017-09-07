package com.shipu.service;

import java.util.List;

import com.shipu.model.Teach;

public interface TeachService {
	 public boolean addTeach(Teach teach);
     public boolean deleteTeach(Teach teach);
     public List<Teach> findStudentsById(Integer teacherId);
     public List<Teach> findTeachersById(Integer studentId);
     public Teach findTeachById(Teach teach);
}
