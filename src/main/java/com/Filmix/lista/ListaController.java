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

@RequestMapping("/lista")
@RestController
public class ListaController {

	@Autowired
	ListaService ls;
	
	@GetMapping("/obtenerLista")
	public List<ListaDTO> obtenerLista() {

		return ls.obtenerLista();

	}

	@PostMapping("/addPeliculasToList")
	public ListaDTO addPeliculas(@RequestParam List<Integer> peliculasIds) {

		return ls.addPeliculasToList(peliculasIds);

	}
	
	@DeleteMapping("/deletePeliculaFromList")
	public void deleFromLista(@RequestParam String pelicula) {

		 ls.borrarPeliculaLista(pelicula);
	}
	
	

}
