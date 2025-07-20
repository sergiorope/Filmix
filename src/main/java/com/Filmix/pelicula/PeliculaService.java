package com.Filmix.pelicula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {

	@Autowired
	PeliculaRepository pr;

	public List<Pelicula> obtenerPeliculas() {

		return pr.findAll();

	}

}
