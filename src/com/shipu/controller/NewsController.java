package com.shipu.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.shipu.model.News;
import com.shipu.service.NewsService;
import com.shipu.utils.ReturnInfo;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
	@Autowired
	private NewsService newsService;
	@RequestMapping(value="/modifyNewsStatus",method=RequestMethod.POST)
	public void modifyNewsStatus(
			@RequestParam("userId")String userId,
			@RequestParam("newsId")String newsId,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		newsService.modifyNewsStatus(Integer.parseInt(newsId));
		result = ReturnInfo.getJsonInfo(data, "更新消息状态成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/deleteNews",method=RequestMethod.POST)
	public void deleteNews(
			@RequestParam("userId")String userId,
			@RequestParam("newsId")String newsId,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		newsService.deleteNews(Integer.parseInt(newsId));
		result = ReturnInfo.getJsonInfo(data, "删除消息成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getAllCommentNews",method=RequestMethod.POST)
	public void getAllCommentNews(
			@RequestParam("userId")String userId,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray commentNews =new JSONArray();
		News news1 = new News();
		news1.setOwnerId(Integer.parseInt(userId));
		news1.setNewsType(0);
		List<News> newsArray = newsService.findNewsByType(news1);
		if(newsArray!=null){
			for(News news :newsArray ){
                JSONObject newsItem = new JSONObject();
                newsItem.put("news", news);
                commentNews.add(newsItem);
			}
		}
		data.put("commentNews", commentNews);
		result = ReturnInfo.getJsonInfo(data, "获取评论消息成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getAllLoveNews",method=RequestMethod.POST)
     public void getAllLoveNews(
 			@RequestParam("userId")String userId,
 			HttpServletRequest req,
 			HttpServletResponse res	

 			) throws IOException{
 		PrintWriter writer = res.getWriter();
 		JSONObject result = new JSONObject();
 		JSONObject data = new JSONObject();
 		JSONArray loveNews =new JSONArray();
 		News news1 = new News();
		news1.setOwnerId(Integer.parseInt(userId));
		news1.setNewsType(1);
		List<News> newsArray = newsService.findNewsByType(news1);
 		if(newsArray!=null){
 			for(News news :newsArray ){
                 JSONObject newsItem = new JSONObject();
                 newsItem.put("news", news);
                 loveNews.add(newsItem);
 			}
 		}
 		data.put("loveNews",loveNews);
 		result = ReturnInfo.getJsonInfo(data, "获取点赞消息成功", 10001, new Date());
 		ReturnInfo.outJson(writer, result);
 	}
	@RequestMapping(value="/getAllTeachNews",method=RequestMethod.POST)
    public void getAllTeachNews(
			@RequestParam("userId")String userId,
			HttpServletRequest req,
			HttpServletResponse res	

			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray teachNews =new JSONArray();
		News news1 = new News();
		news1.setOwnerId(Integer.parseInt(userId));
		news1.setNewsType(2);
		List<News> newsArray = newsService.findNewsByType(news1);
		if(newsArray!=null){
			for(News news :newsArray ){
                JSONObject newsItem = new JSONObject();
                newsItem.put("news", news);
                teachNews.add(newsItem);
			}
		}
		data.put("teachNews",teachNews);
		result = ReturnInfo.getJsonInfo(data, "获取拜师消息成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
    
	@RequestMapping(value="/getNewCommentNews",method=RequestMethod.POST)
	public void getNewCommentNews(
			@RequestParam("userId")String userId,
			HttpServletRequest req,
			HttpServletResponse res	

			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray commentNews =new JSONArray();
		News news1 = new News();
		news1.setOwnerId(Integer.parseInt(userId));
		news1.setNewsType(0);
		List<News> newsArray = newsService.findNewsByTypeAndNew(news1);
		if(newsArray!=null){
			for(News news :newsArray ){
                JSONObject newsItem = new JSONObject();
                newsItem.put("news", news);
                commentNews.add(newsItem);
			}
		}
		data.put("commentNews", commentNews);
		result = ReturnInfo.getJsonInfo(data, "获取评论消息成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/getNewLoveNews",method=RequestMethod.POST)
     public void getNewLoveNews(
 			@RequestParam("userId")String userId,
 			HttpServletRequest req,
 			HttpServletResponse res	

 			) throws IOException{
 		PrintWriter writer = res.getWriter();
 		JSONObject result = new JSONObject();
 		JSONObject data = new JSONObject();
 		JSONArray loveNews =new JSONArray();
 		News news1 = new News();
 		news1.setOwnerId(Integer.parseInt(userId));
		news1.setNewsType(1);
		List<News> newsArray = newsService.findNewsByTypeAndNew(news1);
 		if(newsArray!=null){
 			for(News news :newsArray ){
                 JSONObject newsItem = new JSONObject();
                 newsItem.put("news", news);
                 loveNews.add(newsItem);
 			}
 		}
 		data.put("loveNews",loveNews);
 		result = ReturnInfo.getJsonInfo(data, "获取点赞消息成功", 10001, new Date());
 		ReturnInfo.outJson(writer, result);
 	}
	@RequestMapping(value="/getNewTeachNews",method=RequestMethod.POST)
    public void getNewTeachNews(
			@RequestParam("userId")String userId,
			HttpServletRequest req,
			HttpServletResponse res	

			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray teachNews =new JSONArray();
		News news1 = new News();
 		news1.setOwnerId(Integer.parseInt(userId));
		news1.setNewsType(2);
		List<News> newsArray = newsService.findNewsByTypeAndNew(news1);
		if(newsArray!=null){
			for(News news :newsArray ){
                JSONObject newsItem = new JSONObject();
                newsItem.put("news", news);
                teachNews.add(newsItem);
			}
		}
		data.put("teachNews",teachNews);
		result = ReturnInfo.getJsonInfo(data, "获取拜师消息成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}

}
