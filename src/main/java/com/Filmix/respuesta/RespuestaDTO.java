package com.Filmix.respuesta;

import java.util.List;

import com.Filmix.categoria.Categoria;
import com.Filmix.pregunta.Pregunta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaDTO {
	
	


	private int id;
	private String nombre;
	private int pregunta;
	private List<Integer>listaCategorias;

}
