package com.bolsaideas.springboot.form.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsaideas.springboot.form.app.domain.Pais;
import com.bolsaideas.springboot.form.app.domain.Role;
import com.bolsaideas.springboot.form.app.domain.Usuario;
import com.bolsaideas.springboot.form.app.domain.validation.UsuarioValidator;
import com.bolsaideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.bolsaideas.springboot.form.app.editors.PaisPropertyEditor;
import com.bolsaideas.springboot.form.app.editors.RolePropertyEditor;
import com.bolsaideas.springboot.form.app.exception.UsuarioNoEncontradoException;
import com.bolsaideas.springboot.form.app.service.PaisService;
import com.bolsaideas.springboot.form.app.service.RoleService;
import com.bolsaideas.springboot.form.app.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class FormController {

	@Autowired
	private UsuarioValidator validator;

	@Autowired
	private PaisService paisService;

	@Autowired
	private PaisPropertyEditor paisPropertyEditor;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RolePropertyEditor rolePropertyEditor;
	
	@Autowired
	private UsuarioService usuarioService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
		// Para HTML5 SIEMPRE CON GUION
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// define si el analizador es ligero(lenient)
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, paisPropertyEditor);
		binder.registerCustomEditor(Role.class, rolePropertyEditor);
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario user = new Usuario();
		user.setNombre("Saint");
		user.setApellido("Seiya");
		user.setIdUsuario("12.345.678-A");
		user.setUsername("srlarico");
		user.setEmail("slarico@mail.com");
		user.setCuenta(1234);
		user.setHabilitar(true);
		user.setFechaNacimiento(new Date());
		user.setValorSecreto("Algún valor secreto ******");
		user.setPais(paisService.obtenerPorId(5));
		user.setRoles(List.of(roleService.findByRoleId(2), roleService.findByRoleId(3)));
		// user.setFechaNacimiento(new Date());
		model.addAttribute("titulo", "Formulario de registro");
		model.addAttribute("user", user);
		return "form";
	}

	// @ModelAttribute es el nombre que tomara el objeto validado en la vista
	// automaticamente
	@PostMapping("/form")
	public String procesarForm(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model) {
		// validator.validate(usuario, result);

		if (result.hasErrors()) {
			/*
			 * Forma manual de mandar errores de validacion a la vista Map<String, String>
			 * errors = result.getFieldErrors() .stream() .collect(Collectors.toMap(
			 * FieldError::getField, e -> "El campo ".
			 * concat(e.getField()).concat(" ").concat(e.getDefaultMessage()) ));
			 * model.addAttribute("error", errors);
			 */
			model.addAttribute("titulo", "Resultado form");
			return "form";
		}
		usuarioService.guardarUsuario(usuario);
		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(
			@SessionAttribute(name = "user", required = false) Usuario usuario, 
			Model model,
			SessionStatus status) {
		if (usuario == null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado form");
		status.setComplete();
		return "resultado";
	}
	
	@GetMapping("/ver/{id}")
	public String verUsuario(
			@PathVariable("id") String idUsuario,
			Model model) {
		Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);
		/*if (usuario == null) {
			throw new UsuarioNoEncontradoException(idUsuario);
		}*/
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("user", usuario);
		return "resultado";
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return List.of("Perusalen", "Croacia", "Argentina", "Francia", "Chile", "Mejico");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> mapa = new HashMap<>();
		mapa.put("PER", "Perusalen");
		mapa.put("ESP", "España");
		mapa.put("ARG", "Argentina");
		mapa.put("MX", "Mexico");
		mapa.put("CL", "Chile");
		return mapa;
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listarPaises();
	}

	@ModelAttribute("listaRolesStr")
	public List<String> listaRolesStr() {
		return List.of("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERATOR");
	}

	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return roleService.listarRoles();
	}

	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> mapa = new HashMap<>();
		mapa.put("ROLE_ADMIN", "Administrador");
		mapa.put("ROLE_USER", "Usuario");
		mapa.put("ROLE_MODERATOR", "Moderador");
		return mapa;
	}

	@ModelAttribute("sexo")
	public List<String> sexo() {
		return List.of("Masculino", "Femenino");
	}
	
	@GetMapping("/cerrado")
	public String cerrado(Model model) {
		model.addAttribute("titulo", "Fuera de horario");
		return "fuera_horario";
	}
	
	@GetMapping("/test")
	public String test(Model model) {
		model.addAttribute("titulo", "Fuera de horario");
		//Integer value = 100 / 0;
		Integer value = Integer.parseInt("10x");
		return "test";
	}

}
