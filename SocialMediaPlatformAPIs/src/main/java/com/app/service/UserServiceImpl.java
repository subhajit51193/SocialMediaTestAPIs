package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) throws UserException {
		
		User newUser = userRepository.save(user);
		if (newUser != null) {
			return newUser;
		}
		else {
			throw new UserException("Error");
		}
	}

	@Override
	public User getUserById(Integer id) throws UserException {
		
		if (id == null) {
			throw new UserException("Not found");
		}
		else {
			Optional<User> opt = userRepository.findById(id);
			if (opt == null) {
				throw new UserException("Not found");
			}
			else {
				if (opt.isEmpty()) {
					throw new UserException("Not found");
				}
				else {
					return opt.get();
					
				}
			}
		}
	}

	@Override
	public User updateUserById(User user, Integer id) throws UserException {
		
		Optional<User> opt = userRepository.findById(id);
		if (opt.isEmpty()) {
			throw new UserException("Not found");
		}
		else {
			User updatedUser = opt.get();
			updatedUser.setBio(user.getBio());
			updatedUser.setName(user.getName());
			return userRepository.save(updatedUser);
		}
	}

	@Override
	public User deleteUserById(Integer id) throws UserException {
		
//		if (id == null) {
//			throw new UserException("Not found");
//		}
//		else {
//			Optional<User> opt = userRepository.findById(id);
//			if (opt == null) {
//				throw new UserException("Not found");
//			}
//			else {
//				if (opt.isEmpty()) {
//					throw new UserException("Not found");
//				}
//				else {
//					User user = opt.get();
//					userRepository.delete(user);
//					return user;
//				}
//			}
//		}
		
		Optional<User> opt = userRepository.findById(id);
		if (opt.isEmpty()) {
			throw new UserException("Not found");
		}
		else {
			User user = opt.get();
			userRepository.delete(user);
			return user;
		}
		
		
	}

	@Override
	public Integer getTotalNoOfUsers() throws UserException {
		
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new UserException("Empty List");
		}
		else {
			return users.size();
		}
	}

	@Override
	public List<User> getTop5Users() throws UserException {
		
		List<User> users = userRepository.getTop5Users();
		if (users.isEmpty()) {
			throw new UserException("Empty List");
		}
		else {
			return users;
		}
	}

	
}
