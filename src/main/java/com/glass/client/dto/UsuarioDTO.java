package com.glass.client.dto;

import java.io.Serializable;

import com.glass.client.model.Usuario;

public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8093385621984121073L;
	private Integer id;
	private String usuario;
	private String senha;
	private String permissao;
	private byte[] image; 
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId_usuario();
		this.usuario = usuario.getUsuario();
		this.permissao = usuario.getPermissao();
		this.image = usuario.getImage();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
