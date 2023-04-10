package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.PostException;
import com.app.exception.UserException;
import com.app.model.Post;
import com.app.model.User;
import com.app.service.PostService;
import com.app.service.UserService;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@GetMapping("/users")
	public ResponseEntity<Integer> getTotalNoOfUserhandler() throws UserException{
		Integer res = userService.getTotalNoOfUsers();
		return new ResponseEntity<Integer>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users/top-active")
	public ResponseEntity<List<User>> getTop5Usershandler() throws UserException{
		List<User> users = userService.getTop5Users();
		return new ResponseEntity<List<User>>(users,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<Integer> getTotalNoOfPostHnadler() throws PostException{
		Integer res = postService.getTotalNoOfPost();
		return new ResponseEntity<Integer>(res,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/top-liked")
	public ResponseEntity<List<Post>> getTop5PostHandler() throws PostException{
		List<Post> posts = postService.getTop5Post();
		return new ResponseEntity<List<Post>>(posts,HttpStatus.ACCEPTED);
	}
}
