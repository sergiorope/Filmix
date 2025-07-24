package com.Filmix.pregunta;

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
public class Pregunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String frase;
	
	@OneToMany(mappedBy="pregunta")
	private List<Respuesta> listaRespuestas = new ArrayList<>();


	

}
