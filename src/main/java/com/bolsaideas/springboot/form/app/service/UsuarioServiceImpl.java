package com.bolsaideas.springboot.form.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.domain.Usuario;
import com.bolsaideas.springboot.form.app.exception.UsuarioNoEncontradoException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista;

	public UsuarioServiceImpl() {
		lista = new ArrayList<>();
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return lista;
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		usuario.setIdUsuario(Integer.toString(lista.size()));
		lista.add(usuario);
		return usuario;
	}

	@Override
	public Usuario obtenerUsuarioPorId(String idUsuario) {
		return lista.stream()
				.filter(u -> idUsuario.equals(u.getIdUsuario()))
				.findFirst()
				.orElseThrow(() -> new UsuarioNoEncontradoException(idUsuario));
	}

}
