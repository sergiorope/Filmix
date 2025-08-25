package com.Filmix.pelicula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class PeliculaControllerGraphQL {

	@Autowired
	PeliculaService peliculaService;

	@QueryMapping
	public ResponseEntity<List<PeliculaDTO>> getRecommended(@Argument List<Integer> ids) {

		return ResponseEntity.ok(peliculaService.findRecommended(ids));

	}

}
