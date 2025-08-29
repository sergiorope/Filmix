package com.Filmix.pregunta;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {
	
	@Query("SELECT p FROM Pregunta p")
	public List<Pregunta> findAllPage(Pageable pageable);


}
