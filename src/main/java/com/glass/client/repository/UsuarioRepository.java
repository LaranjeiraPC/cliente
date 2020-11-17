package com.glass.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glass.client.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Usuario findByUsuarioAndSenha(String usuario, String senha);
	
}
