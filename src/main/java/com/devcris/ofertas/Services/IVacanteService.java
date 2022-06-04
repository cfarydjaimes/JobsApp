package com.devcris.ofertas.Services;

import java.util.List;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcris.ofertas.Models.Vacante;

public interface IVacanteService {

    List<Vacante> buscarTodas();

    Vacante buscarPorID(Integer ID);

    void guardar(Vacante vacante);

    List<Vacante> buscarPorDestacadas();

    void eliminar(Integer id);

    List<Vacante> buscarByExample(Example<Vacante> example);

    Page<Vacante>buscarTodas(Pageable page);
}
