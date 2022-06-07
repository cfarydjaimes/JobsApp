package com.devcris.ofertas.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.devcris.ofertas.Models.Perfil;
import com.devcris.ofertas.Models.Usuario;
import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Services.ICategoriaService;
import com.devcris.ofertas.Services.IUsuarioService;
import com.devcris.ofertas.Services.IVacanteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    private IVacanteService serviceVacante;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICategoriaService categoriaService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String registrarse(Usuario usuario) {
        return "usuarios/formRegistro";
    }

    @PostMapping("/signup")
    public String guardarRegistro(Usuario usuario, RedirectAttributes redirectAttributes, BindingResult result) {

        String pwFlat = usuario.getPassword();
        String pwEncript = passwordEncoder.encode(pwFlat);
        usuario.setPassword(pwEncript);
        Perfil perfil = new Perfil();
        perfil.setId(3);
        usuario.agregar(perfil);
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Ocurrio el siguiente error: " + error.getDefaultMessage());
            }
        }
        usuarioService.guardar(usuario);
        redirectAttributes.addFlashAttribute("msg", "Usuario Registrado!");
        return "redirect:/usuarios/index";
    }

    @GetMapping("/")
    public String verHome(Model model) {
        return "home";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/search")
    public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("descripcion",
                ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Vacante> example = Example.of(vacante, exampleMatcher);
        List<Vacante> lista = serviceVacante.buscarByExample(example);
        model.addAttribute("listvacantes", lista);
        return "home";
    }

    @ModelAttribute
    public void setGenericos(Model model) {
        Vacante vacanteSearch = new Vacante();
        vacanteSearch.reset();
        model.addAttribute("listvacantes", serviceVacante.buscarTodas());
        model.addAttribute("categorias", categoriaService.buscarTodas());
        model.addAttribute("search", vacanteSearch);
    }

    @GetMapping("/index")
    public String mostrarIndex(Authentication authenticateAction, HttpSession session) {
        String username = authenticateAction.getName();
        for (GrantedAuthority rol : authenticateAction.getAuthorities()) {
            System.out.println("ROL: " + rol.getAuthority());
        }
        if (session.getAttribute("usuario") == null) {
            Usuario usuario = usuarioService.buscarUsername(username);
            usuario.setPassword(null);
            session.setAttribute("usuario", usuario);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String mostrarLogin(){
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(httpServletRequest, null, null);
        return "redirect:/login";
    }

}



