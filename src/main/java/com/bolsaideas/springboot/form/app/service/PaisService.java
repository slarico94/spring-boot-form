package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import com.bolsaideas.springboot.form.app.domain.Pais;

public interface PaisService {

	List<Pais> listarPaises();

	Pais obtenerPorId(Integer id);

}
