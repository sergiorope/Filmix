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
	PreguntaRepository preguntaRepository;

	public List<PreguntaDTO> findAll() {

		return preguntaRepository.findAllPage(PageRequest.of(0, 5)).stream().map(p -> converToDTO(p)).toList();

	}

	public PreguntaDTO converToDTO(Pregunta pregunta) {
		List<RespuestaDTO> QuestionList = pregunta.getListaRespuestas().stream()
				.map(r -> new RespuestaDTO(r.getId(), r.getNombre(), r.getPregunta().getId(),
						r.getListaCategorias().stream().map(Categoria::getId).collect(Collectors.toList())))
				.collect(Collectors.toList());

		return new PreguntaDTO(pregunta.getId(), pregunta.getFrase(), QuestionList);
	}

}
