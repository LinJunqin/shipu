package com.shipu.mapper;

import java.util.List;

import com.shipu.model.Teach;

public interface TeachMapper {
     public boolean addTeach(Teach teach);
     public boolean deleteTeach(Teach teach);
     public List<Teach> findStudentsById(Integer teacherId);
     public List<Teach> findTeachersById(Integer studentId);
     public Teach findTeachById(Teach teach);
}
