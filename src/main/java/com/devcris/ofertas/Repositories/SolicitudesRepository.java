package com.devcris.ofertas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcris.ofertas.Models.Solicitud;


public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer>{
    
    @Query(nativeQuery = true, value = "SELECT COUNT(*) AS id FROM Solicitudes WHERE idVacante = :idVacante AND idUsuario = :idUsuario")
    public Integer existSolicitud(@Param("idVacante") Integer idVacante, @Param("idUsuario") Integer idUsuario);

}
