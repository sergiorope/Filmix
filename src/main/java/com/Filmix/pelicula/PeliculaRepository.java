package com.Filmix.pelicula;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

	@Query("SELECT p FROM Pelicula p WHERE p.nombre = ?1")
	public Pelicula findByName(String nombre);

	@Query("SELECT p FROM Pelicula p JOIN p.listaCategorias c WHERE c.id IN :idsMax")
	public List<Pelicula> peliculasPorCategoriaRecomendada(List<Integer> idsMax, PageRequest pageRequest);
	
	@Query("SELECT p FROM Pelicula p JOIN p.listaCategorias c WHERE c.id = ?1")
	Optional<List<Pelicula>> findByCategory(int id);
	
	@Modifying
	@Query("DELETE FROM Pelicula p WHERE p.nombre = ?1")
	public void borrarPeliculaPorNombre(String nombre);
}



