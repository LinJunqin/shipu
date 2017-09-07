package com.shipu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shipu.model.Collections;
import com.shipu.model.Level;
import com.shipu.model.Recipe;
import com.shipu.model.User;
import com.shipu.service.CollectionService;
import com.shipu.service.LevelService;
import com.shipu.service.RecipeService;
import com.shipu.service.UserService;
import com.shipu.utils.ReturnInfo;

@Controller
@RequestMapping(value = "/infoSelfmanager")
public class InfoSelfManagerController {
	@Autowired
     private CollectionService collectionService;
	@Autowired
	 private RecipeService recipeService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private UserService userService;
	@RequestMapping(value="/addCollection",method=RequestMethod.POST)
	public void addCollection(
			@RequestParam("userId")String userId,
			@RequestParam("recipeId")String recipeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Collections collection = new Collections(Integer.parseInt(userId),Integer.parseInt(recipeId));
		List<Collections> collections = collectionService.findAllCollectionById(Integer.parseInt(userId));
		for(Collections collectionItem:collections){
			if(collectionItem.getRecipeId()==collection.getRecipeId()){
				result = ReturnInfo.getJsonInfo(data, "收藏重复", 10007, new Date());
				ReturnInfo.outJson(writer, result);
				return;
			}
		}
		collectionService.addCollection(collection);
		Level level = levelService.findLevelById(Integer.parseInt(userId));
		if(level.getSumLevel()<=990){
		  level.setSumLevel(level.getSumLevel()+1);
		  levelService.modifySumLevel(level);  
		}
		result = ReturnInfo.getJsonInfo(data, "收藏成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/deleteCollection",method=RequestMethod.POST)
	public void deleteCollection(
			@RequestParam("userId")String userId,
			@RequestParam("recipeId")String recipeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Collections collection = new Collections(Integer.parseInt(userId),Integer.parseInt(recipeId));
		collectionService.deleteCollection(collection);
		result = ReturnInfo.getJsonInfo(data, "删除收藏成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/findAllCollection",method=RequestMethod.POST)
	public void findAllCollections(
		@RequestParam("userId")String userId,	
		HttpSession session,
		HttpServletRequest req,
		HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray recipeArray = new JSONArray();
		List<Collections> collections = collectionService.findAllCollectionById(Integer.parseInt(userId));
		for(Collections collection:collections){
			Recipe recipe = recipeService.findRecipeById(collection.getRecipeId());
			JSONObject recipeItem = new JSONObject();
			recipeItem.put("recipe", recipe);
			recipeArray.add(recipeItem);
		}
		data.put("recipes", recipeArray);
		result = ReturnInfo.getJsonInfo(data, "获取收藏列表成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/setNickname",method = RequestMethod.POST)
	public void setNickname(
			@RequestParam("userId")Integer userId,
			@RequestParam("newNickname") String newNickname,
			@RequestParam("type")String type,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = userService.getUserById(userId);
		if(user!=null){
			user.setNickname(newNickname);
			userService.setNickname(user);
			result = ReturnInfo.getJsonInfo(data, "修改昵称成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);
		}else{
			result = ReturnInfo.getJsonInfo(data, "修改呢称失败", 10002, new Date());
			ReturnInfo.outJson(writer, result);
		}

	}
	@RequestMapping(value="/setBirthday",method = RequestMethod.POST)
	public void setBirthday(
			@RequestParam("userId")Integer userId,
			@RequestParam("newBirthday") String newBirthday,
			@RequestParam("type")String type,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = userService.getUserById(userId);
		if(user!=null){
			user.setBirthday(newBirthday);
			userService.setBirthday(user);
			result = ReturnInfo.getJsonInfo(data, "修改生日成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);
		}else{
			result = ReturnInfo.getJsonInfo(data, "修改生日失败", 10002, new Date());
			ReturnInfo.outJson(writer, result);
		}

	}
	@RequestMapping(value="/setGender",method = RequestMethod.POST)
	public void setGender(
			@RequestParam("userId")Integer userId,
			@RequestParam("newGender") String newGender,
			@RequestParam("type")String type,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = userService.getUserById(userId);
		if(user!=null){
			user.setGender(newGender);
			userService.setGender(user);
			result = ReturnInfo.getJsonInfo(data, "修改性别成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);
		}else{
			result = ReturnInfo.getJsonInfo(data, "修改性别失败", 10002, new Date());
			ReturnInfo.outJson(writer, result);
		}

	}
	@RequestMapping(value="/setAvatar",method = RequestMethod.POST)
	public void setAvatar(
			@RequestParam("userId")String userId,
			@RequestParam("photoName")String photoName,
			//@RequestParam("file") CommonsMultipartFile file,
			@RequestParam("type")String type,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();	    
		System.out.println(req.getParameter("file"));
		System.out.println(req.getParameter(photoName));
		System.out.println(req.getParameter(type));
	        result = ReturnInfo.getJsonInfo(data, "修改头像成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);
	}
}
