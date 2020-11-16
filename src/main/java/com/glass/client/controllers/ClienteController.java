package com.glass.client.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.glass.client.dto.ClienteDTO;
import com.glass.client.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/consultaCliente")
	public ResponseEntity<List<ClienteDTO>> get() {
		return ResponseEntity.ok(clienteService.consultarCliente());
	}
	
}
