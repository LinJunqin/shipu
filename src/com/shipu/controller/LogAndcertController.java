package com.shipu.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import sun.misc.BASE64Decoder;
import sun.security.provider.MD5;

import com.shipu.model.Follow;
import com.shipu.model.Level;
import com.shipu.model.User;
import com.shipu.service.FollowService;
import com.shipu.service.LevelService;
import com.shipu.service.UserService;
import com.shipu.utils.MD5Utils;
import com.shipu.utils.PhoneFormatCheckUtils;
import com.shipu.utils.ReturnInfo;

@Controller
@RequestMapping(value = "/logAndcert")
public class LogAndcertController {
	@Autowired
	private UserService userService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private FollowService followService;
	private boolean isLogined=false;
	@RequestMapping(value ="/cert",method = RequestMethod.POST)
	public void cert(
			@RequestParam("userPhone")String userPhone,
			@RequestParam("pwd") String pwd,
			@RequestParam("type")String type,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = userService.getUserByPhone(userPhone);
		if(user==null){
			if(PhoneFormatCheckUtils.isPhoneLegal(userPhone)){
				User newUser = new User();
				newUser.setPhoneNum(userPhone);
				newUser.setPwd(pwd);
				//初始化活跃度
				
				boolean isSuccess = userService.addUser(newUser);

				if(isSuccess){
					Level level = new Level();
					level.setUserId(userService.getUserByPhone(userPhone).getUserId());
					level.setStudentLevel(0);
					level.setSumLevel(0);
					level.setTeacherLevel(0);
					levelService.addLevel(level);
					data.put("userId", userService.getUserByPhone(userPhone).getUserId());
					result = ReturnInfo.getJsonInfo(data, "注册成功", 10001, new Date());
					ReturnInfo.outJson(writer, result);
					return;
				}else{

				}
			}else{
				result = ReturnInfo.getJsonInfo(data, "用户已注册", 10007, new Date());
				ReturnInfo.outJson(writer, result);
				return;
			}
		}else{
			result = ReturnInfo.getJsonInfo(data, "手机号码不合法", 10005, new Date());
			ReturnInfo.outJson(writer, result);
			return;
		}
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(
			@RequestParam("userPhone")String userPhone,
			@RequestParam("pwd") String pwd,
			@RequestParam("type")String type,
			@RequestParam("sessionId")String sessionId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res		
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = new User();
		user.setPhoneNum(userPhone);
		user.setPwd(pwd);
		User loginUser = userService.login(user);
		if(loginUser!=null){

			if(sessionId.equals("")&&loginUser.getSessionId().equals("")){
				//初次登录	,同步更新sessionId	
			}else if(sessionId.equals("")&&!loginUser.getSessionId().equals("")){
				//用户在另一部手机上初次登录,同步更新sessionId
				isLogined = true;
			}else if(!sessionId.equals("")&&loginUser.getSessionId().equals(sessionId)){
				//同一部手机检验登录，同步更新sessionId
			}else if(!sessionId.equals("")&&!loginUser.getSessionId().equals(sessionId)){
				//另一部手机下线后登录，同步更新sessionId
				isLogined = true;	
			}
			String newSessionId = MD5Utils.getMD5String(new Date().toString());
			System.out.println(newSessionId);
			
			loginUser.setSessionId(newSessionId);
			userService.setSessionId(loginUser);
			Level level = levelService.findLevelById(loginUser.getUserId());
			List<Follow> follows = followService.findAllFollowerById(loginUser.getUserId());
			
            JSONObject userJson = new JSONObject();
			userJson.put("sessionId", newSessionId);
			userJson.put("userId", loginUser.getUserId());
			userJson.put("phoneNum", loginUser.getPhoneNum());
			userJson.put("nickname", loginUser.getNickname());
			userJson.put("gender", loginUser.getGender());
			userJson.put("birthday", loginUser.getBirthday());
			userJson.put("sumLevel", level.getSumLevel());//总等级来判断是否可以收徒弟
			userJson.put("studentLevel", level.getStudentLevel());
			userJson.put("teacherLevel", level.getTeacherLevel());
            if(follows!=null){
				userJson.put("followers", follows.size());
			}
			data.put("user", userJson);
			result = ReturnInfo.getJsonInfo(data, "登录成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);
			return;
		}else{
			data.put("sessionId", sessionId);
			result = ReturnInfo.getJsonInfo(data, "用户不存在或密码不正确", 10008, new Date());
			ReturnInfo.outJson(writer, result);
			return;
		}

	}
	@RequestMapping(value="/setNewPwd",method=RequestMethod.POST)
	public void setNewPwd(
			@RequestParam("userId")Integer userId,
			@RequestParam("oldPwd") String oldPwd,
			@RequestParam("type")String type,
			@RequestParam("newPwd")String newPwd,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = userService.getUserById(userId);
		if(user!=null){
			if(user.getPwd().equals(oldPwd)){
				user.setPwd(newPwd);
				userService.setPwd(user);
				data.put("newPwd", newPwd);
				result = ReturnInfo.getJsonInfo(data, "修改密码成功", 10001, new Date());
				ReturnInfo.outJson(writer, result);
			}else{
				result = ReturnInfo.getJsonInfo(data, "修改密码失败", 10002, new Date());
				ReturnInfo.outJson(writer, result);
			}
		}else{
			result = ReturnInfo.getJsonInfo(data, "修改密码失败", 10002, new Date());
			ReturnInfo.outJson(writer, result);
		}

	}
	
}
