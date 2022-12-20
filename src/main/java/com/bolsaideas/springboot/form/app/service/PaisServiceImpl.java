package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	
	private List<Pais> lista;
	
	public PaisServiceImpl() {
		this.lista = List.of(
				new Pais(1, "PE", "Perusalen"),
				new Pais(2, "ES", "España"),
				new Pais(3, "AR", "Argentina"),
				new Pais(3, "MX", "Mexico"),
				new Pais(4, "CL", "Chile"),
				new Pais(5, "CO", "Colombia")
						);
	}

	@Override
	public List<Pais> listarPaises() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		return lista.stream()
				.filter(p -> p.getIdPais().equals(id))
				.findFirst()
				.orElse(null);
	}

}
