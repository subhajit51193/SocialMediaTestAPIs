package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("")
	public ResponseEntity<User> createUserHandler(@RequestBody User user) throws UserException{
		User newUser= userService.createUser(user);
		return new ResponseEntity<User>(newUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserByIdHandler(@PathVariable("id") Integer id) throws UserException{
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUserByIdHandler(@RequestBody User user, @PathVariable("id") Integer id) throws UserException{
		User updatedUser = userService.updateUserById(user, id);
		return new ResponseEntity<User>(updatedUser,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserByIdHandler(@PathVariable("id") Integer id) throws UserException{
		User user = userService.deleteUserById(id);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}
}
