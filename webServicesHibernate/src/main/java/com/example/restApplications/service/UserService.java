package com.example.restApplications.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restApplications.dao.UserDao;
import com.example.restApplications.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Transactional
	public void saveOrUpdate(User user) {
		userDao.saveOrUpdate(user);
	}

	@Transactional
	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}

	@Transactional
	public User getUserById(Integer id) {
		return userDao.getUserById(id);
	}
	@Transactional
	public List<User> getUserByIdORName(Integer id,String name) {
		return userDao.getUserByIdORName(id, name);
	}
	
	@Transactional
	public List<User> getUserByName(String name) {
		return userDao.getUserByName(name);
	}
}
