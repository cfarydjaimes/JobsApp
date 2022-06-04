package com.devcris.ofertas.ServicesJPA;

import java.util.List;
import java.util.Optional;

import com.devcris.ofertas.Models.Categoria;
import com.devcris.ofertas.Repositories.CategoriasRepository;
import com.devcris.ofertas.Services.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CategoriasServiceJpa implements ICategoriaService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public void guardar(Categoria categoria) {
        categoriasRepository.save(categoria);
    }

    public List<Categoria> buscarTodas() {
        return categoriasRepository.findAll();
    }

    public Categoria buscarPorId(Integer idCategoria) {
        Optional<Categoria> categoria = categoriasRepository.findById(idCategoria);
        if (categoria.isPresent()) {
            return categoria.get();
        }
        return null;
    }

    public void eliminar(Integer idCategoria) {

        categoriasRepository.deleteById(idCategoria);
    }

    public Page<Categoria> buscarTodas(Pageable page) {
        
        return categoriasRepository.findAll(page);
    }


}
