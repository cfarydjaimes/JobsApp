package com.devcris.ofertas.Controller;

import java.util.Date;
import java.util.List;

import java.text.SimpleDateFormat;

import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Services.ICategoriaService;
import com.devcris.ofertas.Services.IVacanteService;
import com.devcris.ofertas.Util.Utileria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

    @Value("${empleosApp.rout.img}")
    private String ruta;

    @Autowired
    private IVacanteService vacanteService;

    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/create")
    public String crear(Vacante vacante, Model model) {
        model.addAttribute("categorias", categoriaService.buscarTodas());
        return "vacantes/formVacante";
    }

    @PostMapping("/save")
    public String guardar(Vacante vacante, BindingResult result,
            @RequestParam("archivoImagen") MultipartFile multipartFile, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
        }
        if (!multipartFile.isEmpty()) {
            String nombreImagen = Utileria.guardarArchivo(multipartFile, ruta);
            if (nombreImagen != null) { // La imagen si se subio
                // Procesamos la variable nombreImagen
                vacante.setImage(nombreImagen);
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
