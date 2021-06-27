package com.example.restApplications.dao;

import java.util.List;

import com.example.restApplications.entity.User;

public interface UserDao{
	List<User> findAll();
	void saveOrUpdate(User user);
	void deleteUser(Integer id);
	User getUserById(Integer id);
	List<User> getUserByIdORName(Integer id,String name);
	public List<User> getUserByName(String name);
	
}
