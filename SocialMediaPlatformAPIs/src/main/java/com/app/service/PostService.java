package com.app.service;

import java.util.List;

import com.app.exception.PostException;
import com.app.exception.UserException;
import com.app.model.Post;

public interface PostService {

	public Post createPost(Post post, Integer userId) throws PostException;
	
	public Post getPostById(Integer id)throws PostException;
	
	public Post deletePostById(Integer id)throws PostException;
	
	public Post incrementLikeOfPost(Integer postId)throws PostException;
	
	public Post decrementLikeOfPost(Integer postId)throws PostException;
	
	public Integer getTotalNoOfPost()throws PostException;
	
	public List<Post> getTop5Post()throws PostException;;
}
