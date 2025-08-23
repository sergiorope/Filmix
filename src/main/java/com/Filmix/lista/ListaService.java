package com.Filmix.lista;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Filmix.pelicula.Pelicula;
import com.Filmix.pelicula.PeliculaRepository;
import com.Filmix.usuario.Usuario;
import com.Filmix.usuario.UsuarioRepository;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;

@Service
public class ListaService {

	@Autowired
	ListaRepository lr;

	@Autowired
	UsuarioRepository ur;

	@Autowired
	PeliculaRepository pr;
	
	
	public List<ListaDTO> obtenerLista() {
		
		return lr.findAll().stream().map(l->convertirADTO(l)).toList();
	}

	public ListaDTO addPeliculasToList(List<Integer> peliculasIds) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);

		Usuario usuario = ur.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Lista lista = usuario.getLista();

		List<Pelicula> peliculasActuales = lista.getListaPeliculas();

		List<Pelicula> nuevasPeliculas = peliculasIds.stream()
				.filter(id -> peliculasActuales.stream().noneMatch(p -> p.getId() == id))
				.map(id -> pr.findById(id).orElseThrow(() -> new RuntimeException("Película no encontrada: " + id)))
				.collect(Collectors.toList());

		peliculasActuales.addAll(nuevasPeliculas);

		lista.setListaPeliculas(peliculasActuales);

		lr.save(lista);

		return convertirADTO(lista);
	}
	

	public void borrarPeliculaLista(int peliculaId) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);
		
		 lr.eliminarPeliculaDeLista(peliculaId, lr.findByUsuarioId(userId).getId()); 
		
	}


	private ListaDTO convertirADTO(Lista lista) {

		List<String> listaPeliculas = lista.getListaPeliculas().stream().map(Pelicula::getNombre).toList();

		return new ListaDTO(lista.getId(), lista.getUsuario().getNombre(), listaPeliculas);

	}

}
