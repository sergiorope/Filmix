package com.Filmix.pelicula;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

	@Query("SELECT p FROM Pelicula p WHERE p.nombre = ?1")
	public Pelicula nombrePelicula(String nombre);

	@Query("SELECT p FROM Pelicula p JOIN p.listaCategorias c WHERE c.id = ?1")
	public List<Pelicula> peliculaPorCategoria(int id);
}
