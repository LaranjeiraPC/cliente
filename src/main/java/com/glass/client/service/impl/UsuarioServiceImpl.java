package com.glass.client.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glass.client.dto.UsuarioDTO;
import com.glass.client.repository.UsuarioRepository;
import com.glass.client.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	public UsuarioRepository repository;
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UsuarioDTO autenticarUsuario(String usuario, String senha) {
		return new UsuarioDTO(repository.findByUsuarioAndSenha(usuario, senha));
	}

}
