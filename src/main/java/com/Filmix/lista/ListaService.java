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

@Service
public class ListaService {

	@Autowired
	ListaRepository lr;
	
	@Autowired
	UsuarioRepository ur;
	
	@Autowired
	PeliculaRepository pr;
	
	
	public ListaDTO addPeliculasLista(List<Integer> peliculasIds) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Claims claims = (Claims) authentication.getPrincipal();

	    
	    Integer userId = claims.get("id", Integer.class);
	    
	    System.out.println(userId+"WDwdwdww");

	
	    Usuario usuario = ur.findById(userId)
	                            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

	
	    Lista lista = usuario.getLista();
	    if (lista == null) {
	        lista = new Lista();
	        lista.setUsuario(usuario);  
	        usuario.setLista(lista);
	    }
	    
	    List<Pelicula> peliculas = peliculasIds.stream()
	            .map(id -> pr.findById(id)
	                .orElseThrow(() -> new RuntimeException("Película no encontrada: " + id)))
	            .collect(Collectors.toList());
	    
	    lista.setListaPeliculas(peliculas);


	     lr.save(lista);
	     
	     return convertirADTO(lista);
	}



	
	private ListaDTO convertirADTO(Lista lista) {
		
		
		List<String>listaPeliculas = lista.getListaPeliculas().stream().map(Pelicula::getNombre).toList();


	    return new ListaDTO( lista.getId(), lista.getUsuario().getNombre(),listaPeliculas);
	        

	}



}
