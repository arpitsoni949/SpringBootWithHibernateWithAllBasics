package com.example.restApplications.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restApplications.entity.User;
import com.example.restApplications.service.UserService;

@RestController()
@RequestMapping(path="/RestUserController")
public class UserController {
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("all-users")
	public ResponseEntity<List<User>> getAllUsers(){
		logger.info("getAllUsers started");
		List<User> user=userService.findAll();
		
		logger.info("getAllUsers end");
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(value="create-user",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@Valid @RequestBody User user,BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			logger.info("user data has error");
		}
		
		userService.saveOrUpdate(user);
		return ResponseEntity.ok(user);
	}
	@PutMapping(value="update-user",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		
		userService.saveOrUpdate(user);
		return ResponseEntity.ok(user);
	}
	@DeleteMapping(value="delete-user/{id}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable Integer id){
		
		userService.deleteUser(id);
	}
	
	@GetMapping(value="user/{id}")
	public User getUserById(@PathVariable Integer id){
		User user=userService.getUserById(id);
		return user;
	}
	//RequestParam
	@GetMapping(value="user")
	public User getUserByIdUsingRequestParam(@RequestParam Integer id){
		User user=userService.getUserById(id);
		return user;
	}
	@GetMapping(value="userBy/{id}/{name}")
	public List<User> getUserByIdORName(@PathVariable Integer id,@PathVariable String name){
		List<User> userList=userService.getUserByIdORName(id,name);
		return userList;
	}
	
	@GetMapping(value="userByName/{name}")
	public List<User> getUserByName(@PathVariable String name){
		List<User> userList=userService.getUserByName(name);
		return userList;
	}
}
