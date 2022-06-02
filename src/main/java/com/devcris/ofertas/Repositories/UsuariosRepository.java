package com.devcris.ofertas.Repositories;

import com.devcris.ofertas.Models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public  interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    
}
