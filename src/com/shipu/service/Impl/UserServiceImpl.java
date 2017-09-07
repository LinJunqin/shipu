package com.shipu.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shipu.mapper.UserMapper;
import com.shipu.model.User;
import com.shipu.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
    private UserMapper userMapper;

	public User login(User user) {
		
		return userMapper.login(user);
	}

	public List<User> selectAllUser() {
		List<User> users = userMapper.selectAllUser();
		return users;
	}

	public boolean addUser(User user) {
		return userMapper.addUser(user);
	}

	public boolean deleteUser(Integer id) {
		return userMapper.deleteUser(id);
	}

	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	public User getUserByPhone(String phone) {
		return userMapper.getUserByPhone(phone);
	}

	public boolean setPwd(User user) {	
		return userMapper.setPwd(user);
	}

	public boolean setAvatar(User user) {	
		return userMapper.setAvatar(user);
	}

	public boolean setNickname(User user) {
		return userMapper.setNickname(user);
	}

	public boolean setGender(User user) {
		return userMapper.setGender(user);
	}

	public boolean setBirthday(User user) {
		return userMapper.setBirthday(user);
	}

	public boolean setSessionId(User user) {
		
		return userMapper.setSessionId(user);
	}
	

}
