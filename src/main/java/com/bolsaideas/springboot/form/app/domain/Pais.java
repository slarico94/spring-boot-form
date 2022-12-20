package com.bolsaideas.springboot.form.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pais {
	
	//@NotNull
	private Integer idPais;
	
	//@NotEmpty
	private String codigo;
	
	private String nombre;

	@Override
	public String toString() {
		return this.idPais.toString();
	}

}
