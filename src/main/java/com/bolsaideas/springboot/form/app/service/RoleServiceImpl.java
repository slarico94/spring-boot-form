package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.form.app.domain.Role;

@Service
public class RoleServiceImpl implements RoleService {
	
	private List<Role> roles;
	
	
	public RoleServiceImpl() {
		this.roles = List.of(
				new Role(1, "ROLE_ADMIN", "Administrador"),
				new Role(2, "ROLE_USER", "Usuario"),
				new Role(3, "ROLE_MODERATOR", "Moderador")
				);
	}

	@Override
	public List<Role> listarRoles() {
		return roles;
	}

	@Override
	public Role findByRoleId(Integer roleId) {
		return roles.stream()
				.filter(r -> r.getId() == roleId)
				.findFirst()
				.orElse(null);
	}

}
