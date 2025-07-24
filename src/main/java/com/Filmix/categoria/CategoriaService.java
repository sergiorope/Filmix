package com.Filmix.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRespository cr;

	public List<Categoria> obtenerCategorias() {

		return cr.findAll();

	}

}
