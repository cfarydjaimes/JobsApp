package com.devcris.ofertas.Controller;

import com.devcris.ofertas.Models.Categoria;
import com.devcris.ofertas.Services.ICategoriaService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    @Qualifier("categoriasServiceJpa")
    private ICategoriaService categoriaService;

    @GetMapping("/create")
    public String crear(Categoria categoria) {
        return "categorias/formCategoria";
    }

    @PostMapping("/save")
    public String guardar(Categoria categoria, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
        }
        redirectAttributes.addFlashAttribute("msg", "Registro guardado!");
        categoriaService.guardar(categoria);
        return "redirect:/categorias/indexPaginate";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idCategoria, RedirectAttributes redirectAttributes) {

        String IdCat = Integer.toString(idCategoria);
        try {
            redirectAttributes.addFlashAttribute("msg", "Categoria Eliminada!");
            categoriaService.eliminar(idCategoria);

        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msgImpossible", "La categoría con Id " + IdCat
                    + " no puede ser eliminada, porque su registro se encuentra asociado a una vacante.");
        }
        return "redirect:/categorias/indexPaginate";
    }

    @GetMapping("/update/{id}")
    public String actualizar(@PathVariable("id") int idCategoria, RedirectAttributes redirectAttributes, Model model) {
        Categoria categoria = categoriaService.buscarPorId(idCategoria);
        redirectAttributes.addFlashAttribute("msg", "Categoria Actualizada!");
        model.addAttribute("categoria", categoria);
        return "categorias/formCategoria";
    }

    @GetMapping(value = "/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Categoria> list = categoriaService.buscarTodas(page);
        model.addAttribute("categoria", list);
        return "categorias/listCategorias";
    }

}
