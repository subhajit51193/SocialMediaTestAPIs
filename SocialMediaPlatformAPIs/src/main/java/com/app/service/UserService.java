package com.app.service;

import com.app.exception.UserException;
import com.app.model.User;

public interface UserService {

	public User createUser(User user) throws UserException;
	
	public User getUserById(Integer id)throws UserException;
	
	public User updateUserById(User user,Integer id)throws UserException;
	
	public User deleteUserById(Integer id)throws UserException;
}
