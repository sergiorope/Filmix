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
	ListaRepository listaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PeliculaRepository peliculaRepository;

	public List<ListaDTO> findAll() {

		return listaRepository.findAll().stream().map(l -> converToDTO(l)).toList();
	}

	public ListaDTO addFilms(List<Integer> peliculasIds) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);

		Usuario user = usuarioRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

		Lista list = user.getLista();

		List<Pelicula> actualFilms = list.getListaPeliculas();

		List<Pelicula> newFilms = peliculasIds.stream()
				.filter(id -> actualFilms.stream().noneMatch(p -> p.getId() == id))
				.map(id -> peliculaRepository.findById(id).orElseThrow(() -> new RuntimeException("Película no encontrada: " + id)))
				.collect(Collectors.toList());

		actualFilms.addAll(newFilms);

		list.setListaPeliculas(actualFilms);

		listaRepository.save(list);

		return converToDTO(list);
	}

	public void deleteFilm(String pelicula) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);

		
		System.out.println(peliculaRepository.findByName(pelicula).getId() + "despues: "+listaRepository.findByUsuarioId(userId).getId() );
		
		listaRepository.deleteFilm(peliculaRepository.findByName(pelicula).getId(), listaRepository.findByUsuarioId(userId).getId());

	}

	private ListaDTO converToDTO(Lista lista) {

		List<String> listaPeliculas = lista.getListaPeliculas().stream().map(Pelicula::getNombre).toList();

		return new ListaDTO(lista.getId(), lista.getUsuario().getNombre(), listaPeliculas);

	}

}
