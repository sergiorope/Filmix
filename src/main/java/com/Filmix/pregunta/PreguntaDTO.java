package com.Filmix.pregunta;

import java.util.List;

import com.Filmix.respuesta.RespuestaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaDTO {


	private int id;
	private String frase;
	private List<RespuestaDTO> listaRespuestas;

}
