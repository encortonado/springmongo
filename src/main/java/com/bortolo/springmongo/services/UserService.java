package com.bortolo.springmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bortolo.springmongo.domain.User;
import com.bortolo.springmongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		
		return repository.findAll();
		
	}
 	
}
