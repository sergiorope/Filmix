package com.Filmix.lista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ListaDTO> getList() {

		return listaService.findAll();

	}

	@PostMapping
	public ListaDTO addFilms(@RequestParam List<Integer> peliculasIds) {

		return listaService.addFilms(peliculasIds);

	}
	
	@DeleteMapping
	public void deleteFilm(@RequestParam String pelicula) {

		listaService.deleteFilm(pelicula);
	}
	
	

}
