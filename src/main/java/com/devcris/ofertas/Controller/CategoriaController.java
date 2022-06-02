package com.devcris.ofertas.Controller;

import java.util.List;

import com.devcris.ofertas.Models.Categoria;
import com.devcris.ofertas.Services.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    @Qualifier("categoriasServiceJpa")
    private ICategoriaService categoriaService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String verIndex(Model model) {
        List<Categoria> list = categoriaService.buscarTodas();
        model.addAttribute("categoria", list);
        return "categorias/listCategorias";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
        }
        categoriaService.guardar(categoria);
        redirectAttributes.addFlashAttribute("msg", "Registro guardado");
        return "redirect:/categorias/index";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes redirectAttributes) {

        String IdCat = Integer.toString(idCategoria);
        try {
            categoriaService.eliminar(idCategoria);
            redirectAttributes.addFlashAttribute("msg", "Categoria Eliminada!");

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgImpossible", "La categoría con Id " + IdCat
                    + " no puede ser eliminada, porque su registro se encuentra asociado a una vacante.");
        }
        return "redirect:/categorias/index";
    }

    @GetMapping("/update/{id}")
    public String actualizar(@PathVariable("id") int idCategoria, RedirectAttributes redirectAttributes, Model model ){
        Categoria categoria = categoriaService.buscarPorId(idCategoria);
        System.out.println(categoria);
        redirectAttributes.addFlashAttribute("msg", "Categoria Actualizada!");
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria";
    }


}
