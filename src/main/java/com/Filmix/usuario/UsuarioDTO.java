package com.Filmix.usuario;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private int id;
	private String nombre;
	private String correo;
	private String password;

	private List<Double> listaValoraciones;

}
