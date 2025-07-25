package com.Filmix.valoracion;

import com.Filmix.pelicula.Pelicula;
import com.Filmix.reseña.Reseña;
import com.Filmix.usuario.Usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Valoracion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double nota;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;
	
	@OneToOne(mappedBy = "valoracion", optional = true)
    private Reseña reseña;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;


}
