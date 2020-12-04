package com.bortolo.springmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
}
