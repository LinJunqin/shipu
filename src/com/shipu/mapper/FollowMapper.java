package com.shipu.mapper;

import java.util.List;

import com.shipu.model.Follow;

public interface FollowMapper {
     public boolean addFollow(Follow follow);
     public boolean deleteFollow(Follow follow);
     public List<Follow> findAllFollowById(Integer userId);
     public List<Follow> findAllFollowerById(Integer followeeId);
}
