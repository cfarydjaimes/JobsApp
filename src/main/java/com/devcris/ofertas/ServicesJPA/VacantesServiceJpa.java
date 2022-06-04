package com.devcris.ofertas.ServicesJPA;

import java.util.List;
import java.util.Optional;

import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Repositories.VacantesRepository;
import com.devcris.ofertas.Services.IVacanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
@Primary
public class VacantesServiceJpa implements IVacanteService {

    @Autowired
    private VacantesRepository vacantesRepository;

    public List<Vacante> buscarTodas() {
        return vacantesRepository.findAll();
    }

    public Vacante buscarPorID(Integer id) {
        Optional<Vacante> vacante = vacantesRepository.findById(id);
        if (vacante.isPresent()) {
            return vacante.get();
        }
        return null;
    }

    public void guardar(Vacante vacante) {
        vacantesRepository.save(vacante);
    }

    public List<Vacante> buscarPorDestacadas() {
        return vacantesRepository.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    public void eliminar(Integer id) {
        vacantesRepository.deleteById(id);
    }

    public List<Vacante> buscarByExample(Example<Vacante> example) {
        return vacantesRepository.findAll(example);
    }

    public Page<Vacante> buscarTodas(Pageable page) {
        return vacantesRepository.findAll(page);
    }


}
