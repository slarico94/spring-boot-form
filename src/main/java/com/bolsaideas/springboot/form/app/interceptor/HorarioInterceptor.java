package com.bolsaideas.springboot.form.app.interceptor;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("horarioInterceptor")
public class HorarioInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(HorarioInterceptor.class);

	@Value("${horario.abierto}")
	private Integer abierto;

	@Value("${horario.cerrado}")
	private Integer cerrado;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		int hora = LocalDateTime.now().getHour();
		logger.info("Horario del sistema: {}", hora);
		if (hora >= abierto && hora < cerrado) {
			StringBuilder mensaje = new StringBuilder();
			mensaje.append("Bienvenido al horario de atencion a clientes");
			mensaje.append("Atendemos desde: ");
			mensaje.append(abierto);
			mensaje.append("hrs, hasta: ");
			mensaje.append(cerrado);
			mensaje.append("hrs. Gracias por su visita");
			request.setAttribute("horario", mensaje.toString());
			return true;
		}
		response.sendRedirect(request.getContextPath().concat("/cerrado"));
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String mensaje = (String) request.getAttribute("horario");
		if (handler instanceof HandlerMethod) {
			modelAndView.addObject("horario", mensaje);	
		}
	}

}
