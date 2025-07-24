package com.Filmix.pelicula;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Filmix.categoria.CategoriaRespository;

@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository pr;

	public List<Pelicula> obtenerPeliculas() {

		return pr.findAll();

	}

	public List<Pelicula> obtenerPeliculasPorCategoria(int id) {

		return pr.peliculaPorCategoria(id);

	}

	public List<Pelicula> obtenerPeliculasRecomendadas(List<Integer> ids) {

		HashMap<Integer, Integer> map = new HashMap<>();

		ids.stream()
		.forEach(i -> map.put(i, map.getOrDefault(i, 0) + 1));

		int k = map
				.entrySet()
				.stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey).orElseThrow();

		return pr.peliculaPorCategoria(k);

	}

}
