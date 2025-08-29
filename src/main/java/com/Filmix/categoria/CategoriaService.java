package com.Filmix.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRespository categoriaRepository;

	public List<Categoria> findAll() {

		return categoriaRepository.findAll();

	}

}
