package com.glass.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glass.client.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Usuario findByUsuarioAndSenha(String usuario, String senha);
	
	@Query("SELECT u FROM Usuario u WHERE u.id = :id ")
	Usuario consultarPorId(@Param("id") Integer id);
	
}
