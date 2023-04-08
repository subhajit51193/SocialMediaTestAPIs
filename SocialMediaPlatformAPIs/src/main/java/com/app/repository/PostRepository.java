package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
