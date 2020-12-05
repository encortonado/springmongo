package com.bortolo.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bortolo.springmongo.domain.Post;
import com.bortolo.springmongo.resources.util.URL;
import com.bortolo.springmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	PostService service;
	
	@RequestMapping(value = "{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		
		Post post = service.findById(id);
		
		return ResponseEntity.ok().body(post);
	}
	
	@RequestMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(required = true, value = "text", defaultValue = "") String text) {
		
		text = URL.decodeParam(text); // encodando o texto tirando espaços
		List<Post> list = service.findByTitle(text); // buscando o post por titulo
		
		return ResponseEntity.ok().body(list);
	}
	
}
