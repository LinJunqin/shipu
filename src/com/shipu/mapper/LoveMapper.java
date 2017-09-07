package com.shipu.mapper;

import java.util.List;

import com.shipu.model.Love;

public interface LoveMapper {
    public boolean addLove(Love love);
    public boolean deleteLove(Love love);
    public List<Love> findAllRecipeById(Integer userId);
    public List<Love> findAllLoveById(Integer recipeId);
}
