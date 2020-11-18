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
import com.glass.client.util.Response;
import com.glass.client.util.enums.MessageEnum;

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
	public Response update(Integer id, ClienteDTO dto) {
		
		Cliente cliente = repository.consultarPorId(id);
		
		Response response = new Response();
		
		cliente.setNome(dto.getNome());
		cliente.setIdade(dto.getIdade());
		
		try {
			repository.save(cliente);
			response.setMessage("Registro salvo com sucesso!");
			response.setStatus(MessageEnum.SUCESSO.toString());
		}catch(Exception e){
			response.setMessage("Erro ao salvar registro!");
			response.setStatus(MessageEnum.ERROR.toString());
		}
				
		return response;
	}

}
