package com.Filmix.lista;

import java.util.List;
import java.util.NoSuchElementException;
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

		Lista list = getCurrentUser().getLista();

		List<Pelicula> actualUserFilms = list.getListaPeliculas();

		List<Pelicula> newFilms = peliculasIds.stream()
				.filter(id -> actualUserFilms.stream().noneMatch(p -> p.getId() == id))
				.map(id -> peliculaRepository.findById(id)
						.orElseThrow(() -> new NoSuchElementException("Película no encontrada: " + id)))
				.collect(Collectors.toList());

		actualUserFilms.addAll(newFilms);

		list.setListaPeliculas(actualUserFilms);

		listaRepository.save(list);

		return converToDTO(list);
	}

	public void deleteFilm(String pelicula) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);

		listaRepository.deleteFilm(
				peliculaRepository.findByName(pelicula)
						.orElseThrow(() -> new NoSuchElementException("Película no encontrada")).getId(),
				listaRepository.findByUsuarioId(userId)
						.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado")).getId());

	}

	private ListaDTO converToDTO(Lista lista) {

		List<String> listaPeliculas = lista.getListaPeliculas().stream().map(Pelicula::getNombre).toList();

		return new ListaDTO(lista.getId(), lista.getUsuario().getNombre(), listaPeliculas);

	}

	private Usuario getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims) authentication.getPrincipal();

		Integer userId = claims.get("id", Integer.class);

		Usuario user = usuarioRepository.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("Usuario no encontrado"));

		return user;
	}

}
