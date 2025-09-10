package com.Filmix.lista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Filmix.pelicula.Pelicula;

@RequestMapping("/listas")
@RestController
public class ListaController {

	@Autowired
	ListaService listaService;

	@GetMapping
	public ResponseEntity<ListaDTO> getList() {

		return ResponseEntity.ok(listaService.findByUser());

	}

	@PostMapping
	public ResponseEntity<ListaDTO> addFilms(@RequestParam List<Integer> peliculasIds) {

		return ResponseEntity.ok(listaService.addFilms(peliculasIds));

	}

	@DeleteMapping
	public void deleteFilm(@RequestParam String pelicula) {

		listaService.deleteFilm(pelicula);
	}

}
