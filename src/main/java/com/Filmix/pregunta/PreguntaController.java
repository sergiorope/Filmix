package com.Filmix.pregunta;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Filmix.pelicula.PeliculaDTO;

@RequestMapping("/preguntas")
@RestController
public class PreguntaController {

	@Autowired
	PreguntaService ps;

	@GetMapping
	public ResponseEntity<List<PreguntaDTO>> getQuestions() {

		return ResponseEntity.ok(ps.findAll());

	}

}
