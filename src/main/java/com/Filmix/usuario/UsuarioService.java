package com.Filmix.usuario;

import java.util.Date;
import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	


	@Value("${security.jwt.secret-key}")
	private String key;

	@Value("${security.jwt.expiration-time}")
	private long time;

	public String login(String correo, String password) throws Exception {

		Usuario usuario = usuarioRepository.findByEmail(correo)
				.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

		if (!checkPass(password, usuario.getPassword())) {

			throw new AuthenticationException("Error en al loguearse");
		}

		return generateToken(converToDTO(usuario));

	}

	public UsuarioDTO findUser() {

		return usuarioRepository.findById(getCurrentUser().getId()).map(usuario -> converToDTO(usuario))
				.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

	}

	public UsuarioDTO converToDTO(Usuario u) {

		return new UsuarioDTO(u.getId(), u.getNombre(), u.getCorreo(), u.getPassword());

	}

	private Usuario getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);

		Usuario user = usuarioRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

		return user;
	}

	private String generateToken(UsuarioDTO usuario) {
		Date expirationDate = new Date(System.currentTimeMillis() + time);

		return Jwts.builder().setSubject(usuario.getCorreo()).claim("nombre", usuario.getNombre())
				.claim("id", usuario.getId()).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, key).compact();
	}

	public boolean checkPass(String plainPassword, String hashedPassword) {

		if (BCrypt.checkpw(plainPassword, hashedPassword))
			return true;
		else {
			return false;
		}

	}
	
	
}
