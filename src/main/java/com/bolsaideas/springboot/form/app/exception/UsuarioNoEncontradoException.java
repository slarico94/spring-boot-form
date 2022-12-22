package com.bolsaideas.springboot.form.app.exception;

public class UsuarioNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 3627166480370489767L;

	public UsuarioNoEncontradoException(String id) {
		super("Usuario con ID: ".concat(id).concat(" no existe en el sistema"));
	}

}
