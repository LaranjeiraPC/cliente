package com.glass.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.glass.client.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query("SELECT c FROM Cliente c WHERE c.id = :id ")
	Cliente consultarPorId(@Param("id") Integer id);
}
