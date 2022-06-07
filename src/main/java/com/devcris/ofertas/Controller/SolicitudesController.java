package com.devcris.ofertas.Controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
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

import com.devcris.ofertas.Models.Solicitud;
import com.devcris.ofertas.Models.Usuario;
import com.devcris.ofertas.Models.Vacante;
import com.devcris.ofertas.Services.ISolicitudService;
import com.devcris.ofertas.Services.IUsuarioService;
import com.devcris.ofertas.Services.IVacanteService;
import com.devcris.ofertas.Util.Utileria;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {

    @Value("${empleosApp.rout.cv}")
    private String ruta;

    @Autowired
    private ISolicitudService solicitudService;

    @Autowired
    private IVacanteService vacanteService;

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * EJERCICIO: Declarar esta propiedad en el archivo application.properties. El
     * valor sera el directorio
     * en donde se guardarán los archivos de los Curriculums Vitaes de los usuarios.
     */

    @GetMapping("/index")
    public String mostrarIndex(Model model) {
        List<Solicitud> lista = solicitudService.buscarTodas();
        model.addAttribute("solicitud", lista);
        return "solicitud/listSolicitudes";
    }

    /**
     * Metodo que muestra la lista de solicitudes con paginacion
     * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
     * 
     * @return
     */
    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Solicitud> solicitud = solicitudService.buscarTodas(page);
        model.addAttribute("solicitud", solicitud);
        return "solicitud/listSolicitudes";
    }

    /**
     * Método para renderizar el formulario para aplicar para una Vacante
     * Seguridad: Solo disponible para un usuario con perfil USUARIO.
     */

    @GetMapping("/create/{id}")
    public String crear(@PathVariable("id") int idVacante, Solicitud solicitud, Model model) {
        Vacante vacante = vacanteService.buscarPorID(idVacante);
        model.addAttribute("vacante", vacante);
        return "solicitud/formSolicitud";
    }

    /**
     * Método que guarda la solicitud enviada por el usuario en la base de datos
     * Seguridad: Solo disponible para un usuario con perfil USUARIO.
     * 
     * @return
     */
    @PostMapping("/save")
    public String guardar(Solicitud solicitud, BindingResult bindingResult,
            @RequestParam("archivoCV") MultipartFile multipartFile, RedirectAttributes redirectAttributes,
            Authentication authentication) {
        Integer idVacante = solicitud.getVacante().getId();
        String userName = authentication.getName();
        Usuario usuario = usuarioService.buscarUsername(userName);
        int idUsuario = usuario.getId();

        if (solicitudService.existSolicitud(idVacante, idUsuario)) {
            redirectAttributes.addFlashAttribute("msgfail", "Ya te has postulado a esta oferta!");
            return "redirect:/solicitudes/create/" + idVacante;
        }

        if(bindingResult.hasErrors()) {
			for(ObjectError error: bindingResult.getAllErrors()) {
				System.out.println("Ocurrio un error: " + error.getDefaultMessage());
			}
			
			redirectAttributes.addFlashAttribute("msgfail", "Erro al registrar!");
			return "solicitudes/frmSolicitud";
		}
        if (!multipartFile.isEmpty()) {
            String File = Utileria.guardarArchivo(multipartFile, ruta);
            if (File != null) {
                solicitud.setArchivo(File);
            }
        }

        solicitud.setUsuario(usuario);
        solicitud.setFecha(new Date());
        solicitudService.guardar(solicitud);
        redirectAttributes.addFlashAttribute("msg", "Solicitud Enviada!");
        return "redirect:/solicitudes/create/" + idVacante;

    }

    /**
     * Método para eliminar una solicitud
     * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
     * 
     * @return
     */
    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idSolicitud, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("msg", "Solitud Eliminada!");
        solicitudService.eliminar(idSolicitud);
        return "redirect:/solicitudes/indexPaginate";
    }

    /**
     * Personalizamos el Data Binding para todas las propiedades de tipo Date
     * 
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
