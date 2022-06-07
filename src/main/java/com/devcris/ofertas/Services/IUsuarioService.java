package com.devcris.ofertas.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcris.ofertas.Models.Usuario;

public interface IUsuarioService  {
    
    void guardar(Usuario usuario);

    void eliminar(Integer idUsuario);

    List<Usuario> buscarTodos();

    Usuario buscarUsername(String username);

    Page<Usuario> buscarTodas(Pageable page);
}
