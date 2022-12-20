package com.bolsaideas.springboot.form.app.service;

import java.util.List;

import com.bolsaideas.springboot.form.app.domain.Role;

public interface RoleService {

	List<Role> listarRoles();
	
	Role findByRoleId(Integer roleId);
}
