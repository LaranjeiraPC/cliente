package com.glass.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glass.client.dto.ClienteDTO;
import com.glass.client.model.Cliente;
import com.glass.client.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	public ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@CrossOrigin
	@GetMapping("/consultaCliente")
	public ResponseEntity<List<ClienteDTO>> get() {
		return ResponseEntity.ok(clienteService.consultarCliente());
	}

	@CrossOrigin
	@GetMapping("/id/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable(name = "id") Integer id) {

		try {
			return ResponseEntity.ok(clienteService.consultarId(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@CrossOrigin
	@PutMapping("/{id}")
	public ResponseEntity<String> update (
			@PathVariable(name = "id") Integer id, 
			@RequestBody ClienteDTO dto) {
		try {
			return ResponseEntity.ok(clienteService.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
	}

}
