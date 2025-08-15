package com.Filmix.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UsuarioControllerGraphQL {
	
	@Autowired
	UsuarioService us;
	
	@QueryMapping
	public UsuarioDTO obtenerUsuario() {
		
		return us.obtenerUsuario();
		
		
	}

}
