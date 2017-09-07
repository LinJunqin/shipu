package com.shipu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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

import com.shipu.model.Comment;
import com.shipu.model.Follow;
import com.shipu.model.Level;
import com.shipu.model.Love;
import com.shipu.model.News;
import com.shipu.model.Recipe;
import com.shipu.model.Teach;
import com.shipu.model.User;
import com.shipu.service.CommentService;
import com.shipu.service.FollowService;
import com.shipu.service.LevelService;
import com.shipu.service.LoveService;
import com.shipu.service.NewsService;
import com.shipu.service.RecipeService;
import com.shipu.service.TeachService;
import com.shipu.service.UserService;
import com.shipu.utils.ReturnInfo;
@Controller
@RequestMapping(value = "/socialManager")
public class SocialManagerContoller {
	@Autowired
	private CommentService commentService;
	@Autowired
	private FollowService followService;
	@Autowired
	private LoveService loveService;
	@Autowired
	private UserService userService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private TeachService teachService; 
	@Autowired
	private NewsService newsService;
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	public void addComment(
			@RequestParam("userId") String userId,
			@RequestParam("recipeId")String recipeId,
			@RequestParam("content")String content,
			@RequestParam("replyerId")String replyerId,//回复的对方的Id,若不是回复，默认为自己的Id
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user =userService.getUserById(Integer.parseInt(userId));
		Recipe recipe = recipeService.findRecipeById(Integer.parseInt(recipeId));
		if(recipe.getHotLevel()<=990){
			recipe.setHotLevel(recipe.getHotLevel()+1);
			recipeService.setHotLevel(recipe);
		}
		Comment comment = new Comment(Integer.parseInt(userId), Integer.parseInt(recipeId), content,Integer.parseInt(replyerId));
		comment.setCommentDate(new Timestamp(System.currentTimeMillis()));
		commentService.addComment(comment);
		Teach teach = new Teach(Integer.parseInt(userId), recipeService.findRecipeById(Integer.parseInt(recipeId)).getUserId()); 
		Teach teachExist =teachService.findTeachById(teach);
		if(teachExist!=null){
			if(teachService.findStudentsById(Integer.parseInt(userId)).contains(teachExist)){
				//老师评论学生的作品
				Level level = levelService.findLevelById(Integer.parseInt(userId));
				if(level.getTeacherLevel()<=990){
					level.setTeacherLevel(level.getTeacherLevel()+1);
					levelService.modifyTeacherLevel(level);
				}
			}else{
				//徒弟评论老师的作品
				Level level = levelService.findLevelById(Integer.parseInt(userId));
				if(level.getStudentLevel()<=990){
					level.setStudentLevel(level.getStudentLevel()+1);
					levelService.modifyStudentLevel(level);
				}
			}
		}
		Level level = levelService.findLevelById(Integer.parseInt(userId));
		if(level.getSumLevel()<=990){
			level.setSumLevel(level.getSumLevel()+1);
			levelService.modifySumLevel(level);
		}
		//发送消息
		
		if(userId.equals(replyerId)){
			News news = new News(recipe.getUserId(),user.getUserId(),user.getNickname()+"评论了你的"+recipe.getName(),new Timestamp(System.currentTimeMillis()),0,false);
			newsService.addNews(news);
		}else{
			News news = new News(Integer.parseInt(replyerId),user.getUserId(),user.getNickname()+"回复了你",new Timestamp(System.currentTimeMillis()),0,false);
			newsService.addNews(news);
		}
		result = ReturnInfo.getJsonInfo(data, "评论成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/deleteComment" ,method=RequestMethod.POST)
	public void deleteComment(
			@RequestParam("userId")String userId,
			@RequestParam("recipeId")String recipeId,
			@RequestParam("commentId")String commentId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Comment comment = new Comment();
		comment.setCommentId(Integer.parseInt(commentId));
		commentService.deleteComment(comment);
		result = ReturnInfo.getJsonInfo(data, "删除评论成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getAllComments",method=RequestMethod.POST)
	public void getAllCommentsOfRecipe(
			@RequestParam("userId")String userId,
			@RequestParam("recipeId")String recipeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray commentArray = new JSONArray();
		List<Comment> comments = commentService.findCommentById(Integer.parseInt(recipeId));
		for(Comment comment:comments){
			JSONObject commentItem = new JSONObject();
			User user = userService.getUserById(comment.getUserId());
			Level userLevel = levelService.findLevelById(comment.getUserId());
			User replyer = userService.getUserById(comment.getReplyerId());	
			Level replyerLevel = levelService.findLevelById(comment.getReplyerId());
			commentItem.put("user", user);
			commentItem.put("userLevel", userLevel);
			commentItem.put("replyer", replyer);
			commentItem.put("replyerLevel", replyerLevel);
			commentItem.put("content", comment.getContent());
			commentArray.add(commentItem);
		}
		List<Love> loves = loveService.findAllLoveById(Integer.parseInt(recipeId));
		data.put("comments", commentArray);
		data.put("loves", loves.size());//点赞数
		result = ReturnInfo.getJsonInfo(data, "获取评论和点赞数列表成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);	
	}
	@RequestMapping(value="/addLove" ,method=RequestMethod.POST)
	public void addLove(
			@RequestParam("userId")String userId,
			@RequestParam("recipeId")String recipeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		User user =userService.getUserById(Integer.parseInt(userId));
		Recipe recipe = recipeService.findRecipeById(Integer.parseInt(recipeId));
		Love love = new Love(Integer.parseInt(userId),Integer.parseInt(recipeId));
		loveService.addLove(love);
		if(recipe.getHotLevel()<=990){
			recipe.setHotLevel(recipe.getHotLevel()+1);
			recipeService.setHotLevel(recipe);
		}
		Teach teach = new Teach(Integer.parseInt(userId), recipeService.findRecipeById(Integer.parseInt(recipeId)).getUserId()); 
		Teach teachExist =teachService.findTeachById(teach);
		if(teachExist!=null){
			if(teachService.findStudentsById(Integer.parseInt(userId)).contains(teachExist)){
				//老师点赞学生的作品
				Level level = levelService.findLevelById(Integer.parseInt(userId));
				if(level.getTeacherLevel()<=990){
					level.setTeacherLevel(level.getTeacherLevel()+1);
					levelService.modifyTeacherLevel(level);
				}
			}else{
				//徒弟点赞老师的作品
				Level level = levelService.findLevelById(Integer.parseInt(userId));
				if(level.getStudentLevel()<=990){
					level.setStudentLevel(level.getStudentLevel()+1);
					levelService.modifyStudentLevel(level);
				}
			}
		}
		Level level = levelService.findLevelById(Integer.parseInt(userId));
		if(level.getSumLevel()<=990){
			level.setSumLevel(level.getSumLevel()+1);
			levelService.modifySumLevel(level);
		}
		//发送消息
		News news = new News(recipe.getUserId(),user.getUserId(),user.getNickname()+"赞了你的"+recipe.getName(),new Timestamp(System.currentTimeMillis()),1,false);
		newsService.addNews(news);
		result = ReturnInfo.getJsonInfo(data, "点赞成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/deleteLove" ,method=RequestMethod.POST)
	public void deleteLove(
			@RequestParam("userId")String userId,
			@RequestParam("recipeId")String recipeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Love love = new Love(Integer.parseInt(userId),Integer.parseInt(recipeId));
		loveService.deleteLove(love);
		result = ReturnInfo.getJsonInfo(data, "点赞取消", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getAllLoves",method=RequestMethod.POST)
	public void getAllLoves(
			@RequestParam("userId")String userId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray recipeArray = new JSONArray();
		List<Love> loves = loveService.findAllRecipeById(Integer.parseInt(userId));
		for(Love love :loves){
			Recipe recipe = recipeService.findRecipeById( love.getRecipeId());
			JSONObject recipeItem = new JSONObject();
			recipeItem.put("recipe", recipe);
			recipeArray.add(recipeItem);
		}
		data.put("recipes", recipeArray);
		result = ReturnInfo.getJsonInfo(data, "获取点赞列表成功", 10001, new Date());
		ReturnInfo.outJson(writer,result);
	}
	@RequestMapping(value="/addFollow",method =RequestMethod.POST)
	public void addFollow(
			@RequestParam("userId")String userId,
			@RequestParam("followeeId")String followeeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Follow follow = new Follow(Integer.parseInt(userId),Integer.parseInt(followeeId));
		followService.addFollow(follow);
		Teach teach = new Teach(Integer.parseInt(userId), Integer.parseInt(followeeId)); 
		Teach teachExist =teachService.findTeachById(teach);
		if(teachExist!=null){
			if(teachService.findStudentsById(Integer.parseInt(userId)).contains(teachExist)){
				//老师关注学生
				Level level = levelService.findLevelById(Integer.parseInt(userId));
				if(level.getTeacherLevel()<=990){
					level.setTeacherLevel(level.getTeacherLevel()+1);
					levelService.modifyTeacherLevel(level);
				}
			}else{
				//徒弟关注老师
				Level level = levelService.findLevelById(Integer.parseInt(userId));
				if(level.getStudentLevel()<=990){
					level.setStudentLevel(level.getStudentLevel()+1);
					levelService.modifyStudentLevel(level);
				}
			}
		}
		Level level = levelService.findLevelById(Integer.parseInt(userId));
		if(level.getSumLevel()<=990){
			level.setSumLevel(level.getSumLevel()+1);
			levelService.modifySumLevel(level);
		}
		//发送消息
		result = ReturnInfo.getJsonInfo(data, "关注成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/deleteFollow",method=RequestMethod.POST)
	public void deleteFollow(
			@RequestParam("userId")String userId,
			@RequestParam("followeeId")String followeeId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		Follow follow = new Follow(Integer.parseInt(userId),Integer.parseInt(followeeId));
		followService.deleteFollow(follow);
		result = ReturnInfo.getJsonInfo(data, "取消关注成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getAllFollows",method=RequestMethod.POST )
	public void getAllFollows(
			@RequestParam("userId")String userId,
			HttpSession session,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray followArray = new JSONArray();
		List<Follow> follows = followService.findAllFollowById(Integer.parseInt(userId));
		for(Follow follow:follows){
			User user = userService.getUserById(follow.getFolloweeId());
			JSONObject userItem = new JSONObject();
			userItem.put("user", user);
			followArray.add(userItem);
		}
		data.put("follows", followArray);
		result = ReturnInfo.getJsonInfo(data, "获取关注列表成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
}
