package com.shipu.service;

import java.util.List;

import com.shipu.model.User;

public interface UserService {
	public User login(User user);
    public List<User> selectAllUser();
    public boolean addUser(User user);
    public boolean deleteUser(Integer id);
    public User getUserById(Integer id);
    public User getUserByPhone(String phone);
    public boolean setPwd(User user);
    public boolean setAvatar(User user);
    public boolean setNickname(User user);
    public boolean setGender(User user);
    public boolean setBirthday(User user);
    public boolean setSessionId(User user);
}
