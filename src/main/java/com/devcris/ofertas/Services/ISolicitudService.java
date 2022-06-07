package com.devcris.ofertas.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcris.ofertas.Models.Solicitud;

public interface ISolicitudService {
    

    void guardar(Solicitud solicitud);

    void eliminar(Integer idSolicitud);

    List<Solicitud> buscarTodas();

    Solicitud buscarPorId(Integer idSolicitud);

    Page<Solicitud> buscarTodas(Pageable page);

    boolean existSolicitud(Integer idVacante, Integer idUsuario);
}