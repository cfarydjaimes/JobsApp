package com.devcris.ofertas.ServicesJPA;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcris.ofertas.Models.Solicitud;
import com.devcris.ofertas.Repositories.SolicitudesRepository;
import com.devcris.ofertas.Services.ISolicitudService;

@Service
public class SolicitudServiceJpa implements ISolicitudService {

    @Autowired
    private SolicitudesRepository solicitudesRepository;

    public void guardar(Solicitud solicitud) {
        solicitudesRepository.save(solicitud);
    }

    public void eliminar(Integer idSolicitud) {
        solicitudesRepository.deleteById(idSolicitud);
    }

    public List<Solicitud> buscarTodas() {
        return solicitudesRepository.findAll();
    }

    public Solicitud buscarPorId(Integer idSolicitud) {
        Optional<Solicitud> solicitud = solicitudesRepository.findById(idSolicitud);
        if (solicitud.isPresent()) {
            return solicitud.get();
        }
        return null;
    }

    public Page<Solicitud> buscarTodas(Pageable page) {
        return solicitudesRepository.findAll(page);
    }

    public boolean existSolicitud(Integer idVacante, Integer idUsuario) {

        int valorAux = solicitudesRepository.existSolicitud(idVacante, idUsuario);
        return (valorAux >= 1) ? true : false;

    }

}
