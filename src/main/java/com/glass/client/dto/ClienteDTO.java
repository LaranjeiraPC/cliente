package com.glass.client.dto;

import java.io.Serializable;

import com.glass.client.model.Cliente;

public class ClienteDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3842684212926762881L;
	private Integer id;
	private String nome;
	private Integer idade;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.idade = c.getIdade();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
}
