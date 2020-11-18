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
	
	private static final String OK = "Registro Atualizado";
	private static final String ERROR = "Error ao atualizar registro";
	
	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ClienteDTO> consultarCliente() {
		
		List<ClienteDTO> dtoList = new ArrayList<>();
		
		List<Cliente> cliente = repository.findAll();
	
		cliente.forEach(c -> dtoList.add(new ClienteDTO(c)));
		
		return dtoList;
	}

	@Override
	public Cliente consultarId(Integer id) {
		return repository.consultarPorId(id);
	}

	@Override
	public String update(Integer id, ClienteDTO dto) {
		Cliente cliente = repository.consultarPorId(id);
		
		String status = "";
		
		if(cliente != null) {
			cliente.setNome(dto.getNome());
			cliente.setIdade(dto.getIdade());
			
			try {
				repository.save(cliente);
				status = OK;
			}catch(Exception e){
				status = ERROR;
			}
			
		}
		
		return status;
	}

}
