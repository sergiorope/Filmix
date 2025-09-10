package com.Filmix.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UsuarioDTO usuario) throws Exception {
		return ResponseEntity.ok(usuarioService.login(usuario.getCorreo(), usuario.getPassword()));
	}

	

}
