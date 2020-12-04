package com.bortolo.springmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.bortolo.springmongo.domain.User;
import com.bortolo.springmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner{

	
	@Autowired
	UserRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		repository.deleteAll();
		
		User maria = new User(null, "Maria Silva", "maria@gmail.com");
		User alex = new User(null, "Alex Souza", "alex@gmail.com");
		User bob = new User(null, "Bob Santos", "bob@gmail.com");
		
		
		repository.saveAll(Arrays.asList(maria, alex, bob));
		
		
	}

	
	
	
}
