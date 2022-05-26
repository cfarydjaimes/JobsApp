package com.devcris.ofertas.Controller;

import java.util.List;


import com.devcris.ofertas.Models.Categoria;
import com.devcris.ofertas.Services.ICategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
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
        redirectAttributes.addFlashAttribute("msg", "+Registro guardado");
        return "redirect:/categorias/index";
    }
}
