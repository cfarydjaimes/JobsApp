package com.devcris.ofertas.ServicesJPA;

import java.util.List;

import com.devcris.ofertas.Models.Usuario;
import com.devcris.ofertas.Repositories.UsuariosRepository;
import com.devcris.ofertas.Services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public void guardar(Usuario usuario) {
        usuariosRepository.save(usuario);
    }

    public void eliminar(Integer idUsuario) {
        usuariosRepository.deleteById(idUsuario);
    }

    public List<Usuario> buscarTodos() {
        return usuariosRepository.findAll();
    }

    public Usuario buscarUsername(String username) {
        return usuariosRepository.findByUsername(username);
    }

}
