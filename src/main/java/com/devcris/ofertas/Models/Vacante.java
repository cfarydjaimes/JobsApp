package com.devcris.ofertas.Models;

import java.util.Date;

public class Vacante {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Date fecha;
    private double salario;
    private Integer destacado;
    private String image="no-image.png";

    
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Integer getDestacado() {
        return destacado;
    }
    public void setDestacado(Integer destacado) {
        this.destacado = destacado;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    @Override
    public String toString() {
        return "Vacante [descripcion=" + descripcion + ", destacado=" + destacado + ", fecha=" + fecha + ", id=" + id
                + ", nombre=" + nombre + ", salario=" + salario + "]";
    }

    
}
