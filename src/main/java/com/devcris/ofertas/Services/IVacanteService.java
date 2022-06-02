package com.devcris.ofertas.Services;

import java.util.List;

import com.devcris.ofertas.Models.Vacante;

public interface IVacanteService {

    List<Vacante> buscarTodas();

    Vacante buscarPorID(Integer ID);

    void guardar(Vacante vacante);

    List<Vacante> buscarPorDestacadas();

    void eliminar(Integer id);
}
