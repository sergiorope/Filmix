package com.Filmix.pregunta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Filmix.pelicula.PeliculaDTO;

@RequestMapping("/pregunta")
@RestController
public class PreguntaController {
	
	@Autowired
	PreguntaService ps;
	
	@GetMapping("/obtenerPreguntas")
	public ResponseEntity<List<PreguntaDTO>> obtenerPeliculas() {

		List<PreguntaDTO> listaPreguntas = ps.obtenerPreguntas();

		if (listaPreguntas.isEmpty()) {

			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(listaPreguntas);
		}

	}




}
