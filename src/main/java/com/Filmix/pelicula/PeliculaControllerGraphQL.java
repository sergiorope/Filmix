package com.Filmix.pelicula;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PeliculaControllerGraphQL {

	@Autowired
	PeliculaService ps;

	@QueryMapping
	public List<PeliculaDTO> obtenerPeliculasPorRecomendacion(@Argument List<Integer> ids) {

		return ps.obtenerPeliculasRecomendadas(ids);

	}

}
