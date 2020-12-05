package com.bortolo.springmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bortolo.springmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

	
	
}
