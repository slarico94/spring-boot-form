package com.bolsaideas.springboot.form.app.domain;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Usuario {

	//@Pattern(regexp = "[\\d]{2}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")
	private String idUsuario;

	//@NotEmpty(message = "El nombre no puede ser vacio")
	private String nombre;

	@NotBlank
	private String apellido;

	@NotBlank
	@Size(min = 3, max = 8)
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	@Email(message = "Correo con formato incorrecto")
	private String email;
	
	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;
	
	@NotNull
	//@Future
	//@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	
	//@NotEmpty
	@NotNull
	private Pais pais;
	
	//not empty tambien sirve para validar listas vacias
	@NotEmpty
	private List<Role> roles;
	
	private Boolean habilitar;
	
	@NotEmpty
	private String sexo;
	
	private String valorSecreto;

}
