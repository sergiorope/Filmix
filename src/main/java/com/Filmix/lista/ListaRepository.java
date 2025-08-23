package com.Filmix.lista;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Filmix.pelicula.Pelicula;

import jakarta.transaction.Transactional;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Integer> {

	@Query("SELECT l FROM Lista l WHERE l.usuario.id = :usuarioId")
	Lista findByUsuarioId(@Param("usuarioId") int usuarioId);


	@Modifying
	@Transactional
	@Query(value = "DELETE FROM pelicula_lista WHERE pelicula_id = :peliculaId AND lista_id = :listaId", nativeQuery = true)
	void eliminarPeliculaDeLista(@Param("peliculaId") int peliculaId, @Param("listaId") int listaId);

}
