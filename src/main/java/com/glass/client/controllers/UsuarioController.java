package com.glass.client.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glass.client.dto.UsuarioDTO;
import com.glass.client.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	private final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	public UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}

	@CrossOrigin
	@GetMapping("/autenticar/{usuario}/{senha}")
	public ResponseEntity<UsuarioDTO> getAutenticar(
			@PathVariable(name = "usuario") String usuario,
			@PathVariable(name = "senha") String senha) {
		
		log.debug("Autenticar usuario");
		
		try {
			return ResponseEntity.ok(service.autenticarUsuario(usuario, senha));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
}
