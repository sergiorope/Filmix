package com.Filmix.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Filmix.valoracion.Valoracion;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository ur;

	public UsuarioDTO login(String correo) throws Exception {

		Usuario u = ur.findByEmail(correo);
		
		return convertToDTO(u);

	}

	private UsuarioDTO convertToDTO(Usuario u) {

		List<Double> listaValoraciones = u.getListaValoraciones()
				.stream().map(Valoracion::getNota)
				.toList();

		return new UsuarioDTO(u.getId(), 
				u.getNombre(), 
				u.getCorreo(), 
				u.getPassword(), 
				listaValoraciones);

	}

}
