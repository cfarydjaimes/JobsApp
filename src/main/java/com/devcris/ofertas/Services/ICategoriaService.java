package com.devcris.ofertas.Services;

import java.util.List;

import com.devcris.ofertas.Models.Categoria;

public interface ICategoriaService {
    
    void guardar(Categoria categoria);
    List<Categoria> buscarTodas();
    Categoria buscarPorId(Integer idCategoria);
}
