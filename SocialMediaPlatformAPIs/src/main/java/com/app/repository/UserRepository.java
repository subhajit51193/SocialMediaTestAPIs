package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("from User order by posts.size() desc limit 5")
	public List<User> getTop5Users();
}
