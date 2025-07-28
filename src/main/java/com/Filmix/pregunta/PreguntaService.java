package com.Filmix.pregunta;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Filmix.respuesta.Respuesta;

@Service
public class PreguntaService {

	@Autowired
	PreguntaRepository pr;

	public List<PreguntaDTO> obtenerPreguntas() {

		List<Pregunta> listaPreguntas= pr.obtenerPreguntasModal(PageRequest.of(0, 4));
		
		 return listaPreguntas.stream()
				 .map(p -> conversorPreguntaDTO(p))
				 .toList();
		
		

	}
	
	public PreguntaDTO conversorPreguntaDTO(Pregunta pregunta) {
		
		List<String> listaRespuestas = pregunta.getListaRespuestas()
				.stream()
				.map(Respuesta::getNombre)
				.collect(Collectors.toList());
		
		return new PreguntaDTO(pregunta.getId(), pregunta.getFrase(), listaRespuestas);
		
	}

}
