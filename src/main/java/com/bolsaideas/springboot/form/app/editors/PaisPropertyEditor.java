package com.bolsaideas.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bolsaideas.springboot.form.app.service.PaisService;

@Component
public class PaisPropertyEditor extends PropertyEditorSupport {

	@Autowired
	private PaisService paisService;

	@Override
	public void setAsText(String idPaisStr) throws IllegalArgumentException {
		try {
			setValue(paisService.obtenerPorId(Integer.parseInt(idPaisStr)));
		} catch (NumberFormatException e) {
			setValue(null);
		}
	}

}
