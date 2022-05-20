package com.devcris.ofertas.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Services.IVacanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private IVacanteService serviceVacante;

    @GetMapping("/tabla")
    public String mostratTabla(Model model){
        List<Vacante> lista = serviceVacante.buscarTodas();
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

}
