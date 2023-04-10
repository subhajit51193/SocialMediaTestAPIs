package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.PostException;
import com.app.exception.UserException;
import com.app.model.Post;
import com.app.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	@PostMapping("")
	public ResponseEntity<Post> createPostHandler(@RequestBody Post post,@RequestParam("userId") Integer userId) throws UserException, PostException{
		Post newPost = postService.createPost(post, userId);
		return new ResponseEntity<Post>(newPost,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostByIdHandler(@PathVariable("id") Integer id) throws PostException{
		Post post = postService.getPostById(id);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Post> deletePostByIdHandler(@PathVariable("id") Integer id) throws PostException{
		Post post = postService.deletePostById(id);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/{id}/like")
	public ResponseEntity<Post> increaseLikesHandler(@PathVariable("id") Integer id) throws PostException{
		Post post = postService.incrementLikeOfPost(id);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
	@PostMapping("/{id}/unlike")
	public ResponseEntity<Post> decreaseLikesHandler(@PathVariable("id") Integer id) throws PostException{
		Post post = postService.decrementLikeOfPost(id);
		return new ResponseEntity<Post>(post,HttpStatus.ACCEPTED);
	}
}
