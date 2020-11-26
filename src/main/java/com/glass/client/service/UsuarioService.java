package com.glass.client.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.glass.client.dto.UsuarioDTO;
import com.glass.client.model.Usuario;
import com.glass.client.util.Response;

public interface UsuarioService {

	public UsuarioDTO autenticarUsuario(String usuario, String senha);
	
	List<UsuarioDTO> consultarUsuarios();
	
	public Usuario consultarId(Integer id);
	
	public Response update(Integer id, UsuarioDTO dto);
	
	public Response uploadFile(Integer id, MultipartFile file);


}
