package com.devcris.ofertas.Controller;

import java.util.List;

import com.devcris.ofertas.Models.Usuario;
import com.devcris.ofertas.Services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
    
    @Autowired
    @Qualifier("usuarioServiceJpa")
    private IUsuarioService usuarioService;

    @GetMapping("/index")
    public String mostrarIndex(Model model){
        List<Usuario> lista = usuarioService.buscarTodos();
        model.addAttribute("usuario", lista);
        return "usuarios/listUsuarios";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idUsuario, RedirectAttributes redirectAttributes){
        usuarioService.eliminar(idUsuario);
        redirectAttributes.addFlashAttribute("msg", "Usuario eliminado exitosamente!");
        return "redirect:/usuarios/index";
    }

}
