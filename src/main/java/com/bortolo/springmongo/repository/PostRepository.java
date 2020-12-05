package com.bortolo.springmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.bortolo.springmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{'title' : { $regex ?0, $options : 'i'} }")
	List<Post> findByTitle(String text); // faz a mesma coisa que o acima so que com @query
	
}
