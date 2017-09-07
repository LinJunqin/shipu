package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.RecipeMapper;
import com.shipu.model.Recipe;
import com.shipu.service.RecipeService;
@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
	 @Autowired
     private RecipeMapper recipeMapper;

	public boolean addRecipe(Recipe recipe) {
		return recipeMapper.addRecipe(recipe);
	}

	public boolean deleteRecipe(Integer id) {
		return recipeMapper.deleteRecipe(id);
	}

	public List<Recipe> findRecipeByFodder(String fodder) {
		return recipeMapper.findRecipeByFodder(fodder);
	}

	public List<Recipe> findRecipeByKind(String kind) {
		return recipeMapper.findRecipeByKind(kind);
	}

	public List<Recipe> findRecipeBySituation(String situation) {
		return recipeMapper.findRecipeBySituation(situation);
	}

	public List<Recipe> findRecipeByDifficulty(String difficulty) {
		return recipeMapper.findRecipeByDifficulty(difficulty);
	}

	public List<Recipe> findRecipeByName(String name) {
		return recipeMapper.findRecipeByName(name);
	}

	public boolean setFodder(Recipe recipe) {
		return recipeMapper.setFodder(recipe);
	}

	public boolean setKind(Recipe recipe) {
		return recipeMapper.setKind(recipe);
	}

	public boolean setSituation(Recipe recipe) {
		return recipeMapper.setSituation(recipe);
	}

	public boolean setDifficulty(Recipe recipe) {
		return recipeMapper.setDifficulty(recipe);
	}

	public boolean setCookStep(Recipe recipe) {
		return recipeMapper.setCookStep(recipe);
	}

	public boolean setFoodPhotoPath(Recipe recipe) {
		return recipeMapper.setFoodPhotoPath(recipe);
	}

	public boolean setNotification(Recipe recipe) {
		return recipeMapper.setNotification(recipe);
	}

	public boolean setCookVideoPath(Recipe recipe) {
		return recipeMapper.setCookVideoPath(recipe);
	}

	public Recipe findRecipeById(Integer id) {
		return recipeMapper.findRecipeById(id);
	}

	public List<Recipe> findRecipeByHotLevel() {
		return recipeMapper.findRecipeByHotLevel();
	}

	public List<Recipe> findRecipeByUploadDate() {
		return recipeMapper.findRecipeByUploadDate();
	}

	public boolean setHotLevel(Recipe recipe) {
	
		return recipeMapper.setHotLevel(recipe);
	}
}
