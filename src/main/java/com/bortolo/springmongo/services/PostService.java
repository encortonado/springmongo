package com.bortolo.springmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bortolo.springmongo.domain.Post;
import com.bortolo.springmongo.repository.PostRepository;
import com.bortolo.springmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository repository;
	
	public Post findById(String id) {
		
		Optional<Post> post = repository.findById(id);
		
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}
