package com.Filmix.pregunta;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Filmix.categoria.Categoria;
import com.Filmix.respuesta.Respuesta;
import com.Filmix.respuesta.RespuestaDTO;

@Service
public class PreguntaService {

	@Autowired
	PreguntaRepository pr;

	public List<PreguntaDTO> obtenerPreguntas() {

		List<Pregunta> listaPreguntas= pr.obtenerPreguntasModal(PageRequest.of(0, 5));
		
		 return listaPreguntas.stream()
				 .map(p -> conversorPreguntaDTO(p))
				 .toList();
		
		

	}
	
	public PreguntaDTO conversorPreguntaDTO(Pregunta pregunta) {
	    List<RespuestaDTO> listaRespuestas = pregunta.getListaRespuestas()
	            .stream()
	            .map(r -> new RespuestaDTO(
	                    r.getId(),
	                    r.getNombre(),
	                    r.getPregunta().getId(), 
	                    r.getListaCategorias()
	                             .stream()
	                             .map(Categoria::getId) 
	                             .collect(Collectors.toList())
	            ))
	            .collect(Collectors.toList());

	    return new PreguntaDTO(pregunta.getId(), pregunta.getFrase(), listaRespuestas);
	}



}
