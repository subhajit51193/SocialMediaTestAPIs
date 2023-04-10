package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	@Query("from Post order by likes desc limit 5")
	public List<Post> getTop5Post();
}
