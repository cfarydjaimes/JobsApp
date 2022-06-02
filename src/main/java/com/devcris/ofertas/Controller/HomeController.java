package com.devcris.ofertas.Controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.devcris.ofertas.Models.Usuario;
import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Services.IUsuarioService;
import com.devcris.ofertas.Services.IVacanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private IVacanteService serviceVacante;

    @Autowired
    private IUsuarioService usuarioService;

    ////////////////
    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "usuarios/formRegistro";
    }

    /////////////////
    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes redirectAttributes, BindingResult result) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
        }
        usuarioService.guardar(usuario);
        redirectAttributes.addFlashAttribute("msg", "Usuario Registrado!");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/tabla")
    public String mostratTabla(Model model) {

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

    @GetMapping("/")
    public String verHome(Model model) {
        return "home";
    }

    @ModelAttribute
    public void verIndexv2(Model model) {
        model.addAttribute("listvacantes", serviceVacante.buscarPorDestacadas());
    }

}
