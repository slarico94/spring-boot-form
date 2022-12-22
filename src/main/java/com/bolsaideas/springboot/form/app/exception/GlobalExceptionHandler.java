package com.bolsaideas.springboot.form.app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler({ArithmeticException.class})
	public String aritmeticaError(ArithmeticException ex, Model model) {
		log.error("Error de aritmetica: {}", ex);
		model.addAttribute("error", "Error de aritmetica");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDateTime.now());
		return "error/aritmetica";
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatError(NumberFormatException ex, Model model) {
		log.error("Error de formato numero: {}", ex);
		model.addAttribute("error", "Formato número inválido");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDateTime.now());
		return "error/numero";
	}
	
	@ExceptionHandler(UsuarioNoEncontradoException.class)
	public String numberFormatError(UsuarioNoEncontradoException ex, Model model) {
		log.error("Error usuario no encontrado: {}", ex);
		model.addAttribute("error", "Error usuario no encontrado");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDateTime.now());
		return "error/usuario";
	}

}
