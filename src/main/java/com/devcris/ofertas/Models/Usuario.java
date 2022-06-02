package com.devcris.ofertas.Models;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String email;
    private String username;
    private String password;
    private Integer estatus=1;
    private Date fechaRegistro = new Date();

    // Configurar relacion muchos a muchos en springdataJPA
    // EAGER Generera automaticamente las consultas entre tablas asociadas
    // JoinTable Configuramos tabla intermedia mediante parametros
    // name: Nombre de la tabla intermedia
    // joinColumns: Se indica el nombre de la llave foranea
    // inverseJoinColumns: Se indica el nombre de la llave foranea de la tabla 2
    // IMPORTANTE EL ORDEN DE LAS LLAVES FORANEAS
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UsuarioPerfil", joinColumns = @JoinColumn(name = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "idPerfil"))
    private List<Perfil> perfiles;

    public void agregar(Perfil temPerfil) {
        if (perfiles == null) {
            perfiles = new LinkedList<Perfil>();
        }
        temPerfil.setPerfil("USUARIO");
        perfiles.add(temPerfil);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @Override
    public String toString() {
        return "Usuario [email=" + email + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + ", id=" + id
                + ", nombre=" + nombre + ", password=" + password + ", perfiles=" + perfiles + ", username=" + username
                + "]";
    }

}
