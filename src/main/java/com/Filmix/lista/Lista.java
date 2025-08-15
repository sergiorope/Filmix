package com.Filmix.lista;

import java.util.ArrayList;
import java.util.List;

import com.Filmix.pelicula.Pelicula;
import com.Filmix.usuario.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToMany
	@JoinTable(
	    name = "pelicula_lista", 
	    joinColumns = @JoinColumn(name = "lista_id"), 
	    inverseJoinColumns = @JoinColumn(name = "pelicula_id")
	)
	private List<Pelicula> listaPeliculas = new ArrayList<>();



	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	


}
