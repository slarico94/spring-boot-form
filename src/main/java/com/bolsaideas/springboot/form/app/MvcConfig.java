package com.bolsaideas.springboot.form.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	/*@Autowired
	@Qualifier("tiempoTranscurridoInterceptor")
	private HandlerInterceptor tiempoTranscurridoInterceptor;
	
	@Autowired
	@Qualifier("horarioInterceptor")
	private HandlerInterceptor horarioInterceptor;*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(tiempoTranscurridoInterceptor).addPathPatterns("/form/**");
		//registry.addInterceptor(horarioInterceptor).excludePathPatterns("/cerrado/**");
	}

}
