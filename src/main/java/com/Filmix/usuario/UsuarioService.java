package com.Filmix.usuario;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository ur;

	@Value("${security.jwt.secret-key}")
	private String key;

	@Value("${security.jwt.expiration-time}")
	private long time;

	public String login(String correo, String password) {

		Usuario u = ur.findByEmail(correo);

		if (!u.getPassword().equals(password)) {
			throw new RuntimeException("Error la contraseña es incorrecta");
		}

		return generateToken(convertToDTO(u));

	}

	private UsuarioDTO convertToDTO(Usuario u) {

		return new UsuarioDTO(u.getId(), u.getNombre(), u.getCorreo(), u.getPassword());

	}

	private String generateToken(UsuarioDTO usuario) {
		Date expirationDate = new Date(System.currentTimeMillis() + time);

		return Jwts.builder().setSubject(usuario.getCorreo()).claim("nombre", usuario.getNombre())
				.claim("id", usuario.getId()).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}

}
