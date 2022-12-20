package com.bolsaideas.springboot.form.app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private Integer id;

	private String role;

	private String nombre;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof Role)) {
			return false;
		}
		Role role = (Role) obj;
		return this.id != null && this.id.equals(role.getId());
	}

}
