package com.bortolo.springmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bortolo.springmongo.domain.Post;
import com.bortolo.springmongo.domain.User;
import com.bortolo.springmongo.dto.AuthorDTO;
import com.bortolo.springmongo.repository.PostRepository;
import com.bortolo.springmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner{

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Silva", "maria@gmail.com");
		User alex = new User(null, "Alex Souza", "alex@gmail.com");
		User bob = new User(null, "Bob Santos", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem!!!", "Vou viajar para Londres.", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje.", new AuthorDTO(maria));
		
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		
		userRepository.save(maria);
	}

	
	
	
}
