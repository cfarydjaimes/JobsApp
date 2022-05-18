package com.devcris.ofertas.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.devcris.ofertas.Models.Vacante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/tabla")
    public String mostratTabla(Model model){
        List<Vacante> lista = getVacantes();
        model.addAttribute("listvacantes", lista);
        return "tabla";
    }

    @GetMapping("/detalle")
    public String mostrarDetalle(Model model) {
        Vacante vacante = new Vacante();
        vacante.setNombre("Ingeniero de Sistemas");
        vacante.setDescripcion(
                "Oferta laboral para Ingeniero de Sistemas con experiencia en desarrollo web Java Spring boot");
        vacante.setFecha(new Date());
        vacante.setSalario(2500000);
        model.addAttribute("vacante1", vacante);

        return "detalle";
    }

    @GetMapping("/listado")
    public String verListado(Model model) {
        List<String> lista = new LinkedList<String>();
        lista.add("Ingeniero de Sistemas");
        lista.add("Auxiliar de Contabilidad");
        lista.add("Arquitecto");
        lista.add("Auxiliar de obra");

        model.addAttribute("jobs", lista);

        return "listado";
    }

    @GetMapping("/home")
    public String verHome(Model model) {

        String nombre = "Tecnico en Sistemas";
        Date fec = new Date();
        double salario = 9000.0;
        boolean vigente = true;

        model.addAttribute("name", nombre);
        model.addAttribute("date", fec);
        model.addAttribute("salary", salario);
        model.addAttribute("current", vigente);

        return "home";
    }

    private List<Vacante> getVacantes(){

        SimpleDateFormat ff = new SimpleDateFormat("dd-MM-yyyy");
        List<Vacante> lista = new LinkedList<>();

        try{
        Vacante vacante1 = new Vacante();
        vacante1.setId(1);
        vacante1.setNombre("Ingeniero Ambiental");
        vacante1.setDescripcion("Se requiere Ingeniero ambiental cone periencia de 2 años");
        vacante1.setFecha(ff.parse("18-05-2022"));
        vacante1.setSalario(1800000.0);
        vacante1.setDestacado(1);
        vacante1.setImage("empresa2.png");

        Vacante vacante2 = new Vacante();
        vacante2.setId(2);
        vacante2.setNombre("Terapeuta Ocupacional");
        vacante2.setDescripcion("Se requiere Terapeuta ocupacional con experiencia de 1 año");
        vacante2.setFecha(ff.parse("17-05-2022"));
        vacante2.setSalario(2400000.0);
        vacante2.setDestacado(0);
        vacante2.setImage("empresa1.png");

        Vacante vacante3 = new Vacante();
        vacante3.setId(3);
        vacante3.setNombre("Desarrollador de Software");
        vacante3.setDescripcion("Se requiere desarrollador web con experiencia de 4 años");
        vacante3.setFecha(ff.parse("12-05-2022"));
        vacante3.setSalario(2800000.0);
        vacante3.setDestacado(1);
        vacante3.setImage("empresa3.png");
        
        lista.add(vacante1);
        lista.add(vacante2);
        lista.add(vacante3);

        }catch(ParseException e){
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
