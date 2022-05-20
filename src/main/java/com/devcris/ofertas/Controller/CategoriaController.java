package com.devcris.ofertas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/categorias")
public class CategoriaController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String verIndex(Model model){
        return "categorias/listCategorias";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String crear(){
        return "categorias/formCategoria";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion, Model model){
        model.addAttribute("nombreC", nombre);
        model.addAttribute("descripcionC", descripcion);
        return "categorias/listCategorias";
    }
}
