package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.exception.UserException;
import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.service.UserService;

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
	public void createUserWithoutException() throws UserException {
	
		User user = new User();
		user.setId(1);
		user.setName("demo");
		user.setEmail("demo@gmail.com");
		user.setBio("demo bio");
		user.setCreated_at(LocalDateTime.now());
		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
	}

}
