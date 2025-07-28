package com.Filmix.categoria;

import java.util.ArrayList;
import java.util.List;

import com.Filmix.pelicula.Pelicula;
import com.Filmix.respuesta.Respuesta;
import com.Filmix.valoracion.Valoracion;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@ManyToMany
    @JoinTable(
        name = "categoria_pelicula", 
        joinColumns = @JoinColumn(name = "categoria_id"), 
        inverseJoinColumns = @JoinColumn(name = "pelicula_id") 
    )
	private List<Pelicula> listaPeliculas = new ArrayList<>();
	
	
	@ManyToMany(mappedBy="listaCategorias")
	
	private List<Respuesta> listaRespuestas = new ArrayList<>();



	

}
