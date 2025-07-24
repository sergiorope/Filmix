package com.Filmix.respuesta;

import java.util.ArrayList;
import java.util.List;

import com.Filmix.categoria.Categoria;
import com.Filmix.pelicula.Pelicula;
import com.Filmix.pregunta.Pregunta;
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
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@OneToOne
	@JoinColumn(name="pregunta_id")
	private Pregunta pregunta;
	
	@ManyToMany
    @JoinTable(
        name = "respuesta_categoria", 
        joinColumns = @JoinColumn(name = "respuesta_id"), 
        inverseJoinColumns = @JoinColumn(name = "categoria_id") 
    )
	private List<Categoria> listaCategorias = new ArrayList<>();
	
	
	

	

}
