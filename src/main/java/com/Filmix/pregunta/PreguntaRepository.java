package com.Filmix.pregunta;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PreguntaRepository extends JpaRepository<Pregunta, Integer> {
	
	@Query("SELECT p FROM pregunta p")
	public List<Pregunta> obtenerPreguntasModal(Pageable pageable);


}
