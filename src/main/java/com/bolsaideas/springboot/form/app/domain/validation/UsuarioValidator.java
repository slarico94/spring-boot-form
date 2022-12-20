package com.bolsaideas.springboot.form.app.domain.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsaideas.springboot.form.app.domain.Usuario;

@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.user.nombre");
		/*if(usuario.getNombre().isEmpty()) {
			errors.rejectValue("nombre", "NotEmpty.user.nombre");
		}*/
		if (!usuario.getIdUsuario().matches("[\\d]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")) {
			errors.rejectValue("idUsuario", "pattern.user.idUsuario");
		}
	}

}
