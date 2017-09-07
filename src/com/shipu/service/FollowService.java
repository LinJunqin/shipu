package com.shipu.service;

import java.util.List;

import com.shipu.model.Follow;

public interface FollowService {
	 public boolean addFollow(Follow follow);
     public boolean deleteFollow(Follow follow);
     public List<Follow> findAllFollowById(Integer userId); 
     public List<Follow> findAllFollowerById(Integer followeeId);
}
