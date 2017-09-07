package com.shipu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shipu.model.Level;
import com.shipu.model.Recipe;
import com.shipu.model.User;
import com.shipu.service.LevelService;
import com.shipu.service.RecipeService;
import com.shipu.service.UserService;
import com.shipu.utils.ReturnInfo;

@Controller
@RequestMapping(value ="/uploadRecipe")
public class UploadRecipeController {
	@Autowired
	public UserService userService;
	@Autowired
	public RecipeService recipeService;
	@Autowired
	public LevelService levelService;
	@RequestMapping(value="/uploadRecipeText",method=RequestMethod.POST)
	public void uploadRecipe(
			@RequestParam("userId")String userId,
			@RequestParam("fodder")String fodder,
			@RequestParam("kind")String kind,
			@RequestParam("situation")String situation,
			@RequestParam("difficulty")String difficulty,
			@RequestParam("cookStep")String cookStep,
			@RequestParam("name")String name,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException {
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user = userService.getUserById(Integer.parseInt(userId));
		if(user!=null){
			Recipe recipe = new Recipe(name, fodder, kind, situation, difficulty, cookStep, Integer.parseInt(userId));
			String notification = req.getParameter("notification");
			if(notification!=null){
				recipe.setNotification(notification);
			}
			recipe.setUploadDate(new Timestamp(System.currentTimeMillis()));
			recipe.setHotLevel(0);
			recipe.setFoodPhotoPath("");
			recipeService.addRecipe(recipe);
			Level level = levelService.findLevelById(Integer.parseInt(userId));
			if(level.getSumLevel()<=990){
			  level.setSumLevel(level.getSumLevel()+1);
			}
			result = ReturnInfo.getJsonInfo(data, "上传食谱成功", 10001, new Date());
			ReturnInfo.outJson(writer, result);

		}else{
			result = ReturnInfo.getJsonInfo(data, "用户不存在", 10008, new Date());
			ReturnInfo.outJson(writer, result);
		}

	}
	//上传食谱照片
	@RequestMapping(value="/uploadPhoto",method=RequestMethod.POST)
	public void uploadRecipePhoto(
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		result = ReturnInfo.getJsonInfo(data, "上传食谱照片成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	//上传烹饪视频
	@RequestMapping(value="/uploadVideo",method=RequestMethod.POST)
	public void uploadRecipeVideo(
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		result = ReturnInfo.getJsonInfo(data, "上传食成功" ,10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	
}
