package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.LoveMapper;
import com.shipu.model.Love;
import com.shipu.service.LoveService;
@Service
@Transactional
public class LoveServiceImpl implements LoveService {
	@Autowired
    private LoveMapper loveMapper;
	public boolean addLove(Love love) {
		return loveMapper.addLove(love);
	}

	public boolean deleteLove(Love love) {
		return loveMapper.deleteLove(love);
	}

	public List<Love> findAllRecipeById(Integer userId) {
		return loveMapper.findAllRecipeById(userId);
	}

	public List<Love> findAllLoveById(Integer recipeId) {
		return loveMapper.findAllLoveById(recipeId);
	}

}
