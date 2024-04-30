package com.example.tvu_sistema.Controllers.USUARIO;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IGeneroService;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class usuarioGet {
    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IGeneroService generoService;
    @Autowired
	private IProfesionService profesionService;

    @Autowired
	private IUsuarioService usuarioService;


    @RequestMapping(value = "RegistroUsuarioV", method = RequestMethod.GET) // Pagina principal
    public String RegistroUsuarioV(@Validated Usuario usuario,Model model,RedirectAttributes flash,HttpServletRequest request,@RequestParam(name="mensaje",required = false)String mensaje) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("personas", personaService.findAll());
           

            if (mensaje!=null) {
                model.addAttribute("mensaje" , mensaje);
            }
            return "usuario/registroUsuarioA";
        }else{
            return "redirect:/LoginV";
        }
    }

    @PostMapping("/tableUsuario")
    public String tableUsuario(@Validated Usuario usuario, Model model,RedirectAttributes flash,HttpServletRequest request) throws Exception {

        model.addAttribute("usuarios", usuarioService.findAll());
        
        
        
        return "usuario/tableUsuario";
    }

    @PostMapping(value = "/NuevaUsuario")
    public String NuevaUsuario(HttpServletRequest request, Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("personas", personaService.findAll());
        return "usuario/formUsuario";
    }


    @PostMapping(value = "/eliminar-usuario/{id_usuario}")
    @ResponseBody
    public void EliminarUsuario(HttpServletRequest request, Model model,
            @PathVariable("id_usuario") Long id_usuario) {
        Usuario usuario = usuarioService.findOne(id_usuario);
        usuario.setEst_usuario("X");
        usuarioService.save(usuario);
    }

    @RequestMapping(value = "/editar-usuario/{id_usuario}",method = RequestMethod.GET)
    public String editar_usu(@PathVariable("id_usuario") Long id_usuario, Model model) {
        try {
           
            Usuario usuario = usuarioService.findOne(id_usuario);
            model.addAttribute("usuario", usuario);
            model.addAttribute("personas", personaService.findAll());
   
            return "usuario/formUsuario";

        } catch (Exception e) {

            return "redirect:/adm/Bienvenido";
        }
    }

}
