package com.Filmix.categoria;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Filmix.pelicula.Pelicula;
import com.Filmix.respuesta.Respuesta;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRespository categoriaRepository;

	public List<CategoriaDTO> findAll() {

		return categoriaRepository.findAll().stream().map(this::converToDTO).collect(Collectors.toList());

	}

	private CategoriaDTO converToDTO(Categoria categoria) {

		return new CategoriaDTO(categoria.getId(), categoria.getNombre());
	}

}
