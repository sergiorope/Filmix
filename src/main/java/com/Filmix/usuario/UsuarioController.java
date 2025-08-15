package com.Filmix.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
	
	@Autowired
	UsuarioService us;
	
	
	@PostMapping("/login")
	public String login(@RequestBody UsuarioDTO usuario) {
	    return us.login(usuario.getCorreo(), usuario.getPassword());
	}
	
	

}
