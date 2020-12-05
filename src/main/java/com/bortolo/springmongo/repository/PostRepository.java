package com.bortolo.springmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bortolo.springmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	
	
}
