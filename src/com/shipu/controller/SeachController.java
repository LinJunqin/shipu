package com.shipu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shipu.model.Recipe;
import com.shipu.service.RecipeService;
import com.shipu.utils.ReturnInfo;
@Controller
@RequestMapping(value = "/seachRecipe")
public class SeachController {
	@Autowired
	private RecipeService recipeService;


	@RequestMapping(value="/byKey",method=RequestMethod.POST)
	public void seachRecipeByKey(
			@RequestParam("userId")String userId,
			@RequestParam("key")String key,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray recipes = new JSONArray();
		List<Recipe> recipe_1 = recipeService.findRecipeByDifficulty(key);
		List<Recipe> recipe_2 =recipeService.findRecipeByFodder(key);
		List<Recipe> recipe_3 =recipeService.findRecipeByKind(key);
		List<Recipe> recipe_4 =recipeService.findRecipeByName(key);
		List<Recipe> recipe_5 =recipeService.findRecipeBySituation(key);
		if(recipe_1!=null){
			for(Recipe recipe:recipe_1){
                  JSONObject recipeJson = new JSONObject();
                  recipeJson.put("recipe",recipe);
                  recipes.add(recipeJson);
			}
		}
		if(recipe_2!=null){
			for(Recipe recipe:recipe_2){
                  JSONObject recipeJson = new JSONObject();
                  recipeJson.put("recipe",recipe);
                  recipes.add(recipeJson);
			}
		}
		if(recipe_3!=null){
			for(Recipe recipe:recipe_3){
                  JSONObject recipeJson = new JSONObject();
                  recipeJson.put("recipe",recipe);
                  recipes.add(recipeJson);
			}
		}
		if(recipe_4!=null){
			for(Recipe recipe:recipe_4){
                  JSONObject recipeJson = new JSONObject();
                  recipeJson.put("recipe",recipe);
                  recipes.add(recipeJson);
			}
		}
		if(recipe_5!=null){
			for(Recipe recipe:recipe_5){
                  JSONObject recipeJson = new JSONObject();
                  recipeJson.put("recipe",recipe);
                  recipes.add(recipeJson);
			}
		}
		data.put("recipes", recipes);
		result = ReturnInfo.getJsonInfo(data, "搜索成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/byHot",method=RequestMethod.POST)
	public void searchRecipeByHot(
			@RequestParam("userId")String userId,
			HttpServletRequest req,
			HttpServletResponse res
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray recipes = new JSONArray();
		List<Recipe> recipeList = recipeService.findRecipeByHotLevel();
		if(recipeList!=null){
			for(Recipe recipe:recipeList){
                JSONObject recipeJson = new JSONObject();
                recipeJson.put("recipe",recipe);
                recipes.add(recipeJson);
			}
		}
		data.put("recipes", recipes);
		result = ReturnInfo.getJsonInfo(data, "搜索成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
	@RequestMapping(value="/byDate",method=RequestMethod.POST)
	public void searchRecipeByDate(
			@RequestParam("userId")String userId,
			HttpServletRequest req,
			HttpServletResponse res	
			) throws IOException{
		PrintWriter writer = res.getWriter();
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray recipes = new JSONArray();
		List<Recipe> recipeList = recipeService.findRecipeByUploadDate();
		if(recipeList!=null){
			for(Recipe recipe:recipeList){
                JSONObject recipeJson = new JSONObject();
                recipeJson.put("recipe",recipe);
                recipes.add(recipeJson);
			}
		}
		data.put("recipes", recipes);
		result = ReturnInfo.getJsonInfo(data, "搜索成功", 10001, new Date());
		ReturnInfo.outJson(writer, result);
	}
}
