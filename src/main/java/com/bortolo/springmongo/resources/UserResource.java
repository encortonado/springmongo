package com.bortolo.springmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bortolo.springmongo.domain.User;
import com.bortolo.springmongo.dto.UserDTO;
import com.bortolo.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>>  findAll() { // encapsula uma estrutura para retornar respostas http com erros e cabeçalhos
		
		List<User> list = service.findAll(); // pega todos os users da lista e jogam pra userDTO, depois collecta pra uma lista novamente
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	} // .ok() metodo que instancia o responseEntity já com codigo de resposta http de sucesso. (200 ok)
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable(required = true) String id) {
		
		User user = service.findById(id);
		UserDTO userDTO = new UserDTO(user);
		
		return ResponseEntity.ok().body(userDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> insert(@RequestBody UserDTO userDTO) {
		
		User user = service.fromDTO(userDTO);
		service.insert(user);
	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		
		service.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO userDTO) {
		
		User newUser = service.fromDTO(userDTO);
		newUser.setId(id);

		newUser = service.update(newUser);
		
		return ResponseEntity.noContent().build();
		
	}
	
}





