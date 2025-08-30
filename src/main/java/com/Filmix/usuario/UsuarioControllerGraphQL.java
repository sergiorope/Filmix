package com.Filmix.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioControllerGraphQL {

	@Autowired
	UsuarioService userService;

	@QueryMapping
	public UsuarioDTO getUser() {

		return userService.findUser();

	}

}
