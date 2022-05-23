package com.devcris.ofertas.Controller;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;

import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Services.IVacanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Autowired
    private IVacanteService vacanteService;

    @GetMapping("/create")
    public String crear(Vacante vacante) {
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
        }
        vacanteService.guardar(vacante);
        attributes.addFlashAttribute("msg", "Registro Guardado");
        return "redirect:/vacantes/index";
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    // @PostMapping("/save")
    // public String guardar(@RequestParam("nombre") String nombre,
    // @RequestParam("descripcion") String descripcion,
    // @RequestParam("estatus") String estatus, @RequestParam("fecha") String fecha,
    // @RequestParam("destacado") Integer destacado, @RequestParam("salario") double
    // salario,
    // @RequestParam("detalles") String detalles) {

    // System.out.println(nombre + descripcion + estatus + fecha + destacado +
    // salario + detalles);

    // return "vacantes/listVacantes";
    // }

    @GetMapping("/delete")
    public String eliminarVacante(@RequestParam("id") int idVacante, Model model) {

        System.out.println("Borrando vacante con id: " + idVacante);
        model.addAttribute("id", idVacante);
        return "mensaje";
    }

    @GetMapping("/view/{id}")
    public String verDetalle(@PathVariable("id") int idVacante, Model model) {

        Vacante vacante = vacanteService.buscarPorID(idVacante);
        model.addAttribute("vacante", vacante);
        return "detalle";
    }

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Vacante> list = vacanteService.buscarTodas();
        model.addAttribute("vacante", list);
        return "vacantes/listVacantes";
    }

}
