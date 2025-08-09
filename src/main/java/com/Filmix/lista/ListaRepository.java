package com.Filmix.lista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Filmix.pelicula.Pelicula;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Integer> {


}



