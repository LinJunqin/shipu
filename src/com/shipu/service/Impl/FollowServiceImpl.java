package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.FollowMapper;
import com.shipu.model.Follow;
import com.shipu.service.FollowService;
@Service
@Transactional
public class FollowServiceImpl implements FollowService {
	@Autowired
    private FollowMapper followMapper;
	public boolean addFollow(Follow follow) {
		return followMapper.addFollow(follow);
	}

	public boolean deleteFollow(Follow follow) {
		return followMapper.deleteFollow(follow);
	}

	public List<Follow> findAllFollowById(Integer userId) {
		return followMapper.findAllFollowById(userId);
	}
	 public List<Follow> findAllFollowerById(Integer followeeId){
		 return followMapper.findAllFollowerById(followeeId);
	 }
}
