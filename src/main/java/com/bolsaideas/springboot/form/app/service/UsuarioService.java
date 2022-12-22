package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import com.bolsaideas.springboot.form.app.domain.Usuario;

public interface UsuarioService {

	List<Usuario> listarUsuarios();
	
	Usuario guardarUsuario(Usuario usuario);
	
	Usuario obtenerUsuarioPorId(String idUsuario);
}
