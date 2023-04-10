package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.PostException;
import com.app.exception.UserException;
import com.app.model.Post;
import com.app.model.User;
import com.app.repository.PostRepository;
import com.app.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Post createPost(Post post, Integer userId) throws PostException {
		
		Optional<User> opt = userRepository.findById(userId);
		if (opt.isEmpty()) {
			throw new PostException("Not found");
		}
		else {
			User user = opt.get();
			post.setUser(user);
			Post newPost = postRepository.save(post);
			user.getPosts().add(newPost);
			userRepository.save(user);
			if (newPost != null) {
				return newPost;
			}
			else {
				throw new PostException("Not found");
			}
			
		}
	}

	@Override
	public Post getPostById(Integer id) throws PostException {
		
		if (id == null) {
			throw new PostException("Not found");
		}
		else {
			Optional<Post> opt = postRepository.findById(id);
			if (opt == null) {
				throw new PostException("Not found");
			}
			else {
				if (opt.isEmpty()) {
					throw new PostException("Not found");
				}
				else {
					return opt.get();
				}
			}
		}
	}

	@Override
	public Post deletePostById(Integer id) throws PostException {
		
		if (id == null) {
			throw new PostException("Not found");
		}
		else {
			Optional<Post> opt = postRepository.findById(id);
			if (opt == null) {
				throw new PostException("Not found");
			}
			else {
				if (opt.isEmpty()) {
					throw new PostException("Not found");
				}
				else {
					Post post = opt.get();
					postRepository.delete(post);
					return post;
				}
			}
		}
		
	}

	@Override
	public Post incrementLikeOfPost(Integer postId) throws PostException {
		
		if (postId == null) {
			throw new PostException("not found");
		}
		else {
			Optional<Post> opt = postRepository.findById(postId);
			if (opt.isEmpty()) {
				throw new PostException("Not found");
			}else {
				Post post = opt.get();
				post.setLikes(post.getLikes()+1);
				return postRepository.save(post);
				
			}
		}
	}

	@Override
	public Post decrementLikeOfPost(Integer postId) throws PostException {
	
		if (postId == null) {
			throw new PostException("not found");
		}
		else {
			Optional<Post> opt = postRepository.findById(postId);
			if (opt.isEmpty()) {
				throw new PostException("Not found");
			}else {
				Post post = opt.get();
				if (post.getLikes() == 0) {
					throw new PostException("Error");
				}
				else {
					post.setLikes(post.getLikes()-1);
					return postRepository.save(post);
				}
			}
		}
	}

	@Override
	public Integer getTotalNoOfPost() throws PostException {
		
		List<Post> posts = postRepository.findAll();
		if (posts.isEmpty()) {
			throw new PostException("Empty");
		}
		else {
			return posts.size();
		}
	}

	@Override
	public List<Post> getTop5Post() throws PostException {
		
		List<Post> posts = postRepository.getTop5Post();
		if (posts.isEmpty()) {
			throw new PostException("Empty");
		}
		else {
			return posts;
		}
	}

	
}
