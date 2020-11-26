package com.glass.client.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.glass.client.dto.UsuarioDTO;
import com.glass.client.model.Usuario;
import com.glass.client.repository.UsuarioRepository;
import com.glass.client.service.UsuarioService;
import com.glass.client.util.Response;
import com.glass.client.util.enums.MessageEnum;

/**
 * @author leonardo.l.oliveira
 *
 */
/**
 * @author leonardo.l.oliveira
 *
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	public UsuarioRepository repository;

	public UsuarioServiceImpl(UsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public UsuarioDTO autenticarUsuario(String usuario, String senha) {		
		return new UsuarioDTO(repository.findByUsuarioAndSenha(usuario, senha));
	}

	@Override
	public List<UsuarioDTO> consultarUsuarios() {
		List<UsuarioDTO> dto = new ArrayList<>();

		List<Usuario> user = repository.findAll();
		user.forEach(d -> dto.add(new UsuarioDTO(d)));

		return dto;
	}

	@Override
	public Usuario consultarId(Integer id) {
		return repository.consultarPorId(id);
	}

	@Override
	public Response update(Integer id, UsuarioDTO dto) {

		Usuario usuario = repository.consultarPorId(id);

		Response response = new Response();

		usuario.setPermissao(dto.getPermissao());

		try {
			repository.save(usuario);
			response.setMessage("Registro salvo com sucesso!");
			response.setStatus(MessageEnum.SUCESSO.toString());
		} catch (Exception e) {
			response.setMessage("Erro ao salvar registro!");
			response.setStatus(MessageEnum.ERROR.toString());
		}

		return response;
	}

	@Override
	public Response uploadFile(Integer id, MultipartFile file) {
		Response response = new Response();

		Usuario usuario = repository.consultarPorId(id);

		try {

			//usuario.setImage(compressBytes(file.getBytes()));
			usuario.setImage(file.getBytes());

			repository.save(usuario);
			response.setMessage("Registro salvo com sucesso!");
			response.setStatus(MessageEnum.SUCESSO.toString());
		} catch (Exception e) {
			response.setMessage("Erro ao salvar registro!");
			response.setStatus(MessageEnum.ERROR.toString());
		}

		return response;
	}

	/**
	 * Comprimir arquivo do tipo imagem
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[50];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[50];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
				System.out.println("Count - " + count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
