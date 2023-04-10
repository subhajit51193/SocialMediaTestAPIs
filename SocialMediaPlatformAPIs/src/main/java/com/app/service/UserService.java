package com.app.service;

import java.util.List;

import com.app.exception.UserException;
import com.app.model.User;

public interface UserService {

	public User createUser(User user) throws UserException;
	
	public User getUserById(Integer id)throws UserException;
	
	public User updateUserById(User user,Integer id)throws UserException;
	
	public User deleteUserById(Integer id)throws UserException;
	
	public Integer getTotalNoOfUsers()throws UserException;
	
	public List<User> getTop5Users()throws UserException;
	
}
