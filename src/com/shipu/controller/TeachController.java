package com.shipu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shipu.model.Level;
import com.shipu.model.News;
import com.shipu.model.Teach;
import com.shipu.model.User;
import com.shipu.service.LevelService;
import com.shipu.service.NewsService;
import com.shipu.service.TeachService;
import com.shipu.service.UserService;
import com.shipu.utils.ReturnInfo;

@Controller
@RequestMapping(value = "/teachManager")
public class TeachController {
	@Autowired
	private LevelService levelService;
	@Autowired
	private TeachService teachService;
	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	@RequestMapping(value="/addTeacher",method=RequestMethod.POST)
	public void addTeacher(
			@RequestParam("studentId") String studentId,
			@RequestParam("teacherId")String teacherId,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException {
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User student = userService.getUserById(Integer.parseInt(studentId));
		User teacher = userService.getUserById(Integer.parseInt(teacherId));
		//向老师发消息
		News news= new News(teacher.getUserId(),student.getUserId(),student.getNickname()+"请求拜你为师!",new Timestamp(System.currentTimeMillis()),2,false);
		newsService.addNews(news);
		result = ReturnInfo.getJsonInfo(data, "请求发送成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
		
		//Level level = levelService.findLevelById(Integer.parseInt(teacherId));
        
	}
	@RequestMapping(value="/replyToStudent",method=RequestMethod.POST)
	public void makeTeacher(
			@RequestParam("studentId") String studentId,
			@RequestParam("teacherId")String teacherId,
			@RequestParam("replyContent")String replyContent,//答复的内容  0-拒绝   1-接受
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User student = userService.getUserById(Integer.parseInt(studentId));
		User teacher = userService.getUserById(Integer.parseInt(teacherId));
		if(replyContent.equals("0")){
			//向学生请求者发生请求失败的消息
			News news= new News(student.getUserId(),teacher.getUserId(),teacher.getNickname()+"拒绝了你的拜师请求!",new Timestamp(System.currentTimeMillis()),2,false);
			newsService.addNews(news);
			result = ReturnInfo.getJsonInfo(data, "回复成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);

		}else if(replyContent.equals("1")){
			//增加师徒关系
			Teach teach = new Teach(Integer.parseInt(studentId),Integer.parseInt(teacherId));
			Teach teachExist = teachService.findTeachById(teach);
			if(teachExist==null){
				teachService.addTeach(teach);
				//向学生请求者发送成功的消息
				News news= new News(student.getUserId(),teacher.getUserId(),teacher.getNickname()+"接受你的拜师请求!",new Timestamp(System.currentTimeMillis()),2,false);
				newsService.addNews(news);
				//更新活跃度
				Level teacherLevel = levelService.findLevelById(Integer.parseInt(teacherId));
				Level studentLevel = levelService.findLevelById(Integer.parseInt(studentId));
				if(teacherLevel.getTeacherLevel()<=990){
					teacherLevel.setTeacherLevel(teacherLevel.getTeacherLevel()+2);
					levelService.modifyTeacherLevel(teacherLevel);
				}
				if(teacherLevel.getSumLevel()<=990){
					teacherLevel.setSumLevel(teacherLevel.getSumLevel()+1);
					levelService.modifySumLevel(teacherLevel);
				}
				if(studentLevel.getStudentLevel()<=990){
					studentLevel.setStudentLevel(studentLevel.getStudentLevel()+2);
					levelService.modifyStudentLevel(studentLevel);
				}if(studentLevel.getSumLevel()<=990){
					studentLevel.setSumLevel(studentLevel.getSumLevel()+1);
					levelService.modifySumLevel(studentLevel);
				}
				result = ReturnInfo.getJsonInfo(data, "回复成功", 10001, new Date());
				ReturnInfo.outJson(writer, result);
				
			}else{
				result = ReturnInfo.getJsonInfo(data, "关系已存在", 10002, new Date());
				ReturnInfo.outJson(writer, result);
			}

		}else{
			result = ReturnInfo.getJsonInfo(data, "参数错误", 10002, new Date());
			ReturnInfo.outJson(writer, result);
		}
	}
	@RequestMapping(value="/deleteTeach",method=RequestMethod.POST)
	public void deleteTeach(
			@RequestParam("studentId") String studentId,
			@RequestParam("teacherId")String teacherId,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Teach teach = new Teach(Integer.parseInt(studentId), Integer.parseInt(teacherId));
		teachService.deleteTeach(teach);
		result = ReturnInfo.getJsonInfo(data, "解除关系成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getTeachers",method=RequestMethod.POST)
   public void getTeachers(
		   @RequestParam("userId")String userId,
		   HttpServletRequest req,
			HttpServletResponse res
		   ) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray teacherArray = new JSONArray();
		List<Teach> teachers = teachService.findTeachersById(Integer.parseInt(userId));
		for(Teach teach :teachers){
			User user = userService.getUserById(teach.getTeacherId());
			Level level = levelService.findLevelById(teach.getTeacherId());
			JSONObject teachItem = new JSONObject();
			teachItem.put("teacher",user);
			teachItem.put("teacherLevel", level);
			teacherArray.add(teachItem);
		}
		data.put("teachers", teacherArray);
		result = ReturnInfo.getJsonInfo(data, "获取师傅成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
   }
	@RequestMapping(value="/getStudents",method=RequestMethod.POST)
	   public void getStudents(
			   @RequestParam("userId")String userId,
			   HttpServletRequest req,
				HttpServletResponse res
			   ) throws IOException{
			PrintWriter writer = res.getWriter();
			JSONObject result = new JSONObject();
			JSONObject data = new JSONObject();
			JSONArray studentArray = new JSONArray();
			List<Teach> students = teachService.findStudentsById(Integer.parseInt(userId));
			for(Teach teach :students){
				User user = userService.getUserById(teach.getStudentId());
				Level level = levelService.findLevelById(teach.getStudentId());
				JSONObject studentItem = new JSONObject();
				studentItem.put("student",user);
				studentItem.put("studentLevel", level);
				studentArray.add(studentItem);
			}
			data.put("students", studentArray);
			result = ReturnInfo.getJsonInfo(data, "获取徒弟成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);
	   }
}
