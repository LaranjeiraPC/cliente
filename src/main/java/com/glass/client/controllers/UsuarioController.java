package com.glass.client.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.glass.client.dto.UsuarioDTO;
import com.glass.client.model.Usuario;
import com.glass.client.service.UsuarioService;
import com.glass.client.util.Response;

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
	public ResponseEntity<UsuarioDTO> getAutenticar(@PathVariable(name = "usuario") String usuario,
			@PathVariable(name = "senha") String senha) {

		log.debug("Autenticar usuario");

		try {
			return ResponseEntity.ok(service.autenticarUsuario(usuario, senha));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@CrossOrigin
	@GetMapping("/id/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable(name = "id") Integer id) {

		try {
			return ResponseEntity.ok(service.consultarId(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@CrossOrigin
	@GetMapping("/consultar")
	public ResponseEntity<List<UsuarioDTO>> getConsultarUsuarios() {

		log.debug("Consultar usuários");

		try {
			return ResponseEntity.ok(service.consultarUsuarios());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<Response> update(@PathVariable(name = "id") Integer id, @RequestBody UsuarioDTO dto) {

		Response response = new Response();

		try {
			response = service.update(id, dto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}

	@CrossOrigin
	@PostMapping("/upload-image/{id}")
	public ResponseEntity<Response> uploadImage (
			@RequestParam("image") MultipartFile file,
			@PathVariable(name = "id") Integer id) {
		
		Response response = new Response();
		
		try {
			if (!file.isEmpty()) {
				 response = service.uploadFile(id, file);
			} 
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
		return ResponseEntity.ok(response);	
	}

}
