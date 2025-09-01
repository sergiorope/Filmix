package com.Filmix.categoria;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Filmix.pelicula.Pelicula;
import com.Filmix.pelicula.PeliculaDTO;
import com.Filmix.pelicula.PeliculaService;
import com.Filmix.respuesta.Respuesta;

@RequestMapping("/categorias")
@RestController
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> getCategories() {

		return ResponseEntity.ok(categoriaService.findAll());

	}

}
