package com.glass.client.service;

import java.util.List;

import com.glass.client.dto.ClienteDTO;
import com.glass.client.model.Cliente;

public interface ClienteService {
	
	public List<ClienteDTO> consultarCliente();
	
	public Cliente consultarId(Integer id);
	
	public String update(Integer id, ClienteDTO dto);

}
