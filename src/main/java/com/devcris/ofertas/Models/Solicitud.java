package com.devcris.ofertas.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fecha;

    private String comentarios;

    private String archivo;

    @OneToOne
    @JoinColumn(name = "idVacante")
    private Vacante vacante;

    @OneToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Solicitud() {
    }

    public Solicitud(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Solicitud [archivo=" + archivo + ", comentarios=" + comentarios + ", fecha=" + fecha + ", id=" + id
                + ", usuario=" + usuario + ", vacante=" + vacante + "]";
    }
}
