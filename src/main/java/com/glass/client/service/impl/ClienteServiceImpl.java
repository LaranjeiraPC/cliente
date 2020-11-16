package com.glass.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glass.client.dto.ClienteDTO;
import com.glass.client.model.Cliente;
import com.glass.client.repository.ClienteRepository;
import com.glass.client.service.ClienteService;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	public ClienteRepository repository;
	
	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ClienteDTO> consultarCliente() {

//		ClienteDTO dto = new ClienteDTO();
//		dto.setId(1);
//		dto.setNome("Victor Laranjeira de Oliveira");
//		dto.setIdade(21);
		
		List<ClienteDTO> dtoList = new ArrayList<>();
		
		List<Cliente> cliente = repository.findAll();
	
		cliente.forEach(c -> dtoList.add(new ClienteDTO(c)));
		
		return dtoList;
	}

}
