package com.devcris.ofertas.Repositories;

import com.devcris.ofertas.Models.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
    
}
