package com.bortolo.springmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bortolo.springmongo.domain.User;
import com.bortolo.springmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>>  findAll() { // encapsula uma estrutura para retornar respostas http com erros e cabeçalhos
		
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	} // .ok() metodo que instancia o responseEntity já com codigo de resposta http de sucesso. (200 ok)
	
}
