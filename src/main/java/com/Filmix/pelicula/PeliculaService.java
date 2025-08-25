package com.Filmix.pelicula;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Filmix.categoria.Categoria;
import com.Filmix.categoria.CategoriaRespository;

@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository peliculaRepository;

	public List<PeliculaDTO> findAll() {

		return peliculaRepository.findAll().stream().map(this::converToDTO).collect(Collectors.toList());

	}

	public List<PeliculaDTO> findByCategory(int id) {

		return peliculaRepository.findByCategory(id).stream().map(this::converToDTO).collect(Collectors.toList());

	}

	public List<PeliculaDTO> findRecommended(List<Integer> ids) {

		HashMap<Integer, Integer> mostRepeatedFilmsMap = new HashMap<>();

		ids.stream().forEach(i -> mostRepeatedFilmsMap.put(i, mostRepeatedFilmsMap.getOrDefault(i, 0) + 1));

		int mostRepeatedFilmValue = mostRepeatedFilmsMap.entrySet().stream().max(Map.Entry.comparingByValue())
				.map(Map.Entry::getValue).orElseThrow();

		List<Integer> mostRepeatedFilmKey = mostRepeatedFilmsMap.entrySet().stream()
				.filter(e -> e.getValue().equals(mostRepeatedFilmValue)).map(Map.Entry::getKey)
				.collect(Collectors.toList());

		return peliculaRepository.peliculasPorCategoriaRecomendada(mostRepeatedFilmKey, PageRequest.of(0, 2)).stream()
				.map(this::converToDTO).collect(Collectors.toList());

	}

	private PeliculaDTO converToDTO(Pelicula pelicula) {
		List<String> categoryNames = pelicula.getListaCategorias().stream().map(Categoria::getNombre)
				.collect(Collectors.toList());

		List<String> RaitingResume = pelicula.getListaValoraciones().stream().map(v -> "Puntaje: " + v.getNota())
				.collect(Collectors.toList());

		return new PeliculaDTO(pelicula.getId(), pelicula.getNombre(), pelicula.getSinopsis(), pelicula.getImagen(),
				categoryNames, RaitingResume);
	}

}
