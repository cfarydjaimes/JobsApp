package com.devcris.ofertas.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.devcris.ofertas.Models.Vacante;

import org.springframework.stereotype.Service;

@Service
public class VacanteService implements IVacanteService {

    private List<Vacante> lista = null;

    public VacanteService() {

        SimpleDateFormat ff = new SimpleDateFormat("dd-MM-yyyy");
        lista = new LinkedList<>();

        try {
            Vacante vacante1 = new Vacante();
            vacante1.setId(1);
            vacante1.setNombre("Ingeniero Ambiental");
            vacante1.setDescripcion("Se requiere Ingeniero ambiental cone periencia de 2 años");
            vacante1.setFecha(ff.parse("18-05-2022"));
            vacante1.setSalario(1800000.0);
            vacante1.setDestacado(1);
            vacante1.setEstatus("Aprovada");
            vacante1.setImage("logo6.png");

            Vacante vacante2 = new Vacante();
            vacante2.setId(2);
            vacante2.setNombre("Terapeuta Ocupacional");
            vacante2.setDescripcion("Se requiere Terapeuta ocupacional con experiencia de 1 año");
            vacante2.setFecha(ff.parse("17-05-2022"));
            vacante2.setSalario(2400000.0);
            vacante2.setDestacado(0);
            vacante2.setEstatus("Creada");
            vacante2.setImage("logo2.png");

            Vacante vacante3 = new Vacante();
            vacante3.setId(3);
            vacante3.setNombre("Desarrollador de Software");
            vacante3.setDescripcion("Se requiere desarrollador web con experiencia de 4 años");
            vacante3.setFecha(ff.parse("12-05-2022"));
            vacante3.setSalario(2800000.0);
            vacante3.setDestacado(1);
            vacante3.setEstatus("Creada");
            vacante3.setImage("logo3.png");

            lista.add(vacante1);
            lista.add(vacante2);
            lista.add(vacante3);

        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public List<Vacante> buscarTodas() {
        return lista;
    }

    public Vacante buscarPorID(Integer ID) {
        for (Vacante v : lista) {
            if (v.getId() == ID) {
                return v;
            }
        }
        return null;
    }

    @Override
    public void guardar(Vacante vacante) {
        lista.add(vacante);
    }
}
