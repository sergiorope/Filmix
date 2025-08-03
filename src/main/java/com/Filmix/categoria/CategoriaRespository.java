package com.Filmix.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRespository extends JpaRepository<Categoria, Integer> {


}
