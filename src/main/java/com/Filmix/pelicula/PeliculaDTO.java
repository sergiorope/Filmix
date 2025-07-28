package com.Filmix.pelicula;

import java.util.ArrayList;
import java.util.List;

import com.Filmix.categoria.Categoria;
import com.Filmix.valoracion.Valoracion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PeliculaDTO {

	private int id;
	private String nombre;
	private String sinopsis; 
	private String imagen;

	private List<String> listaCategorias = new ArrayList<>();
	private List<String> listaValoraciones = new ArrayList<>();

	

}
