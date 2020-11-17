package com.glass.client.service;

import com.glass.client.dto.UsuarioDTO;

public interface UsuarioService {

	public UsuarioDTO autenticarUsuario(String usuario, String senha);

}
