package com.devcris.ofertas.Services;

import java.util.List;

import com.devcris.ofertas.Models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoriaService {

    void guardar(Categoria categoria);

    List<Categoria> buscarTodas();

    Categoria buscarPorId(Integer idCategoria);

    void eliminar(Integer idCategoria);

    Page<Categoria>buscarTodas(Pageable page);
}
