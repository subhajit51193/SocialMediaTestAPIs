package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@RunWith(SpringRunner.class)
class SocialMediaPlatformApIsApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	
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
	
	@Test
	public void deleteUserTest() throws UserException {
		
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		
		userService.deleteUserById(user.getId());
		verify(userRepository,times(1)).delete(user);
	}

}
