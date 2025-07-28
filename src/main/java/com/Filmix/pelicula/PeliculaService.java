package com.Filmix.pelicula;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Filmix.categoria.Categoria;
import com.Filmix.categoria.CategoriaRespository;

@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository pr;

	public List<PeliculaDTO> obtenerPeliculas() {

		List<Pelicula> listaPeliculas = pr.findAll();
		
		return listaPeliculas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());

	}

	public List<PeliculaDTO> obtenerPeliculasPorCategoria(int id) {

		
				
				List<Pelicula> pelicula =pr.peliculaPorCategoria(id);
				
				return pelicula.stream()
		                .map(this::convertirADTO)
		                .collect(Collectors.toList());

	}

	public List<PeliculaDTO> obtenerPeliculasRecomendadas(List<Integer> ids) {

		HashMap<Integer, Integer> map = new HashMap<>();

		ids.stream()
		.forEach(i -> map.put(i, map.getOrDefault(i, 0) + 1));

		int k = map
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getValue).orElseThrow();
		
		System.out.println(k);
		

		List<Integer> keys= map.entrySet()
		.stream()
		.filter(e -> e.getValue().equals(k))
		.map(Map.Entry::getKey)
		.collect(Collectors.toList());
		
		System.out.println(ids);
		System.out.println(keys);
		System.out.println(map);
		
	    List<Pelicula> peliculas = pr.peliculasPorCategoriaRecomendada(keys);
	    
	    


		return peliculas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());

	}
	
	private PeliculaDTO convertirADTO(Pelicula pelicula) {
	    List<String> nombresCategorias = pelicula.getListaCategorias()
	                                            .stream()
	                                            .map(Categoria::getNombre)
	                                            .collect(Collectors.toList());

	    List<String> resumenValoraciones = pelicula.getListaValoraciones()
	                                              .stream()
	                                              .map(v -> "Puntaje: " + v.getNota()) 
	                                              .collect(Collectors.toList());

	    return new PeliculaDTO(
	        pelicula.getId(),
	        pelicula.getNombre(),
	        pelicula.getSinopsis(),
	        pelicula.getImagen(),
	        nombresCategorias,
	        resumenValoraciones
	    );
	}



}
