package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.PostException;
import com.app.exception.UserException;
import com.app.model.Post;
import com.app.model.User;
import com.app.repository.PostRepository;
import com.app.repository.UserRepository;
import com.app.service.PostService;
import com.app.service.UserService;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@RunWith(SpringRunner.class)
class SocialMediaPlatformApIsApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Autowired
	private PostService postService;
	
	@MockBean
	private PostRepository postRepository;
	
	
	@Test
	public void createUserWithExceptionTest() {
		
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		user.setCreated_at(LocalDateTime.now());
		
		when(userRepository.save(user)).thenReturn(null);
		assertThrows(UserException.class, 
				() ->{
					userService.createUser(user);
				});
	}
	
	@Test
	public void createUserWithoutExceptionTest() throws UserException {
	
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		user.setCreated_at(LocalDateTime.now());
		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
	}
	
	@Test
	public void getUserByIdWithExceptionTest() {
		when(userRepository.findById(anyInt())).thenReturn(null);
		assertThrows(UserException.class,
				() ->{
					userService.getUserById(anyInt());
				});
	}
	
	@Test
	public void getUserByIdWithoutException() throws UserException {
		
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		
		when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
		assertEquals(Optional.of(user).get(), userService.getUserById(anyInt()));
	}
	
//	@Test
//	public void deleteUserTest() throws UserException {
//		
//		User user = new User();
//		user.setId(1);
//		user.setName("demo");
//		user.setEmail("demo@gmail.com");
//		user.setBio("demo bio");
//		
//		userService.deleteUserById(user.getId());
//		verify(userRepository,times(1)).delete(user);
//	}
	
	@Test
	public void getTotalNoOfUsersWithExceptionTest() {
		
		when(userRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(UserException.class, 
				() ->{
					userService.getTotalNoOfUsers();
				});
	}
	
	@Test
	public void getTotalNoOfUsersWithoutExceptionTest() throws UserException {
		
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		
		User user1 = new User();
		user.setId(2);
		user.setName("demo2");
		user.setEmail("demo2@gmail.com");
		user.setBio("demo2 bio");
		
		when(userRepository.findAll()).thenReturn(Stream
				.of(user,user1).collect(Collectors.toList()));
		assertEquals((int)2, (int)userService.getTotalNoOfUsers());
	}
	
	@Test
	public void createPostWithExceptionTest() {
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		
		Post post = new Post();
		post.setId(1);
		post.setUser(user);
		post.setContent("Demo");
		post.setLikes(0);
		
		when(postRepository.save(post)).thenReturn(null);
		assertThrows(PostException.class, 
				() ->{
					postService.createPost(post, anyInt());
				});
	}
	
	@Test
	public void getPostByIdWithExceptionTest() {
		
		when(postRepository.findById(anyInt())).thenReturn(null);
		assertThrows(PostException.class,
				() ->{
					postService.getPostById(anyInt());
				});
	}
	
	@Test
	public void getPostByIdWithoutExceptionTest() throws PostException {
		
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		
		Post post = new Post();
		post.setId(1);
		post.setUser(user);
		post.setContent("Demo");
		post.setLikes(0);
		
		when(postRepository.findById(anyInt())).thenReturn(Optional.of(post));
		assertEquals(Optional.of(post).get(), postService.getPostById(anyInt()));
	}
	
	@Test
	public void getTotalNoOfPostsWithExceptionTest() {
		
		when(postRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(PostException.class, 
				() ->{
					postService.getTotalNoOfPost();
				});
	}
	
	@Test
	public void getTotalNoOfPostsWithoutExceptionTest() throws PostException {
		
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		
		Post post = new Post();
		post.setId(1);
		post.setUser(user);
		post.setContent("Demo");
		post.setLikes(0);
		
		Post post1 = new Post();
		post1.setId(2);
		post1.setUser(user);
		post1.setContent("Demo1");
		post1.setLikes(0);
		
		
		when(postRepository.findAll()).thenReturn(Stream
				.of(post,post1).collect(Collectors.toList()));
		assertEquals((int)2, (int)postService.getTotalNoOfPost());
		
	}

}
