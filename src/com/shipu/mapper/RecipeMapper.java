package com.shipu.mapper;

import java.util.List;

import com.shipu.model.Recipe;

public interface RecipeMapper {
       public boolean addRecipe(Recipe recipe);
       public boolean deleteRecipe(Integer id);
       public Recipe findRecipeById(Integer id);
       public List<Recipe> findRecipeByFodder(String fodder);//模糊查找
       public List<Recipe> findRecipeByKind(String kind);
       public List<Recipe> findRecipeBySituation(String situation);
       public List<Recipe> findRecipeByDifficulty(String difficulty);
       public List<Recipe> findRecipeByName(String name);
       public List<Recipe> findRecipeByHotLevel();//最热的 10 条
       public List<Recipe> findRecipeByUploadDate();//最新上传，10条
       public boolean setFodder(Recipe recipe);
       public boolean setKind(Recipe recipe);
       public boolean setSituation(Recipe recipe);
       public boolean setDifficulty(Recipe recipe);
       public boolean setCookStep(Recipe recipe);
       public boolean setFoodPhotoPath(Recipe recipe);
       public boolean setNotification(Recipe recipe);
       public boolean setCookVideoPath(Recipe recipe); 
       public boolean setHotLevel(Recipe recipe);
}
