package com.devcris.ofertas.Services;

import java.util.LinkedList;
import java.util.List;

import com.devcris.ofertas.Models.Categoria;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService implements ICategoriaService {

    private List<Categoria> lista = null;

    public CategoriaService() {
        
        lista = new LinkedList<>();

        Categoria categoria1 = new Categoria();
        categoria1.setId(1);
        categoria1.setNombre("Ingenieria");
        categoria1.setDescripcion("Categoria relacionada con la ingenieria");

        lista.add(categoria1);

    }


    public void guardar(Categoria categoria) {
        lista.add(categoria);
    }

    public List<Categoria> buscarTodas() {
        return lista;
    }

    public Categoria buscarPorId(Integer id) {

        for (Categoria c : lista) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void eliminar(Integer idCategoria) {

        
    }

}
