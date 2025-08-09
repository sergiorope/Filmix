package com.Filmix.lista;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
 @AllArgsConstructor
 @NoArgsConstructor
public class ListaDTO {

	private int id;
	
	private String usuario;


	private List<String> listaPeliculas;

	

	


}
