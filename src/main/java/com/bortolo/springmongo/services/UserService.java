package com.bortolo.springmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bortolo.springmongo.domain.User;
import com.bortolo.springmongo.dto.UserDTO;
import com.bortolo.springmongo.repository.UserRepository;
import com.bortolo.springmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {

		return repository.findAll();

	}

	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {

		return repository.insert(user);

	}

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
	
	public void delete(String id) {
		
		findById(id);
		repository.deleteById(id);
		
	}
	
	public User update(User newUser) {
		
		User oldUser = findById(newUser.getId());
		
		updateData(oldUser, newUser);
		
		return repository.save(oldUser);
		
	}

	private void updateData(User oldUser, User newUser) {
		oldUser.setName(newUser.getName());
		oldUser.setEmail(newUser.getEmail());
		
	}
}
