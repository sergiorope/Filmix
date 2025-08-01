package com.Filmix.pelicula;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pelicula")
@RestController
public class PeliculaController {

	@Autowired
	PeliculaService ps;

	@GetMapping("/obtenerPeliculas")
	public ResponseEntity<List<PeliculaDTO>> obtenerPeliculas() {

		List<PeliculaDTO> listaPeliculas = ps.obtenerPeliculas();

		if (listaPeliculas.isEmpty()) {

			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok(listaPeliculas);
		}

	}

	@GetMapping("/obtenerPeliculasRecomendada")
	public ResponseEntity<List<PeliculaDTO>> obtenerPeliculasRecomendada(@RequestParam List<Integer> ids) {
	    System.out.println("IDs recibidos: " + ids);
	    List<PeliculaDTO> listaPeliculas = ps.obtenerPeliculasRecomendadas(ids);

	    if (listaPeliculas.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(listaPeliculas);
	    }
	}


}
