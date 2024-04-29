package com.example.tvu_sistema.Controllers.PERSONA;

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
import com.example.tvu_sistema.Models.IService.IGeneroService;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class personaGet {
    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IGeneroService generoService;
    @Autowired
	private IProfesionService profesionService;

    @Autowired
	private IUsuarioService usuarioService;


    @RequestMapping(value = "RegistroPersonaV", method = RequestMethod.GET) // Pagina principal
    public String RegistroPersonaV(@Validated Persona persona,Model model,RedirectAttributes flash,HttpServletRequest request,@RequestParam(name="mensaje",required = false)String mensaje) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("profesiones", profesionService.findAll());

            if (mensaje!=null) {
                model.addAttribute("mensaje" , mensaje);
            }
            return "persona/registroPersonaA";
        }else{
            return "redirect:/LoginV";
        }
    }

    @PostMapping("/tablePersona")
    public String tablePersona(@Validated Persona persona, Model model,RedirectAttributes flash,HttpServletRequest request) throws Exception {

        model.addAttribute("personas", personaService.findAll());
        
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("profesiones", profesionService.findAll());
        
        return "persona/tablePersona";
    }

    @PostMapping(value = "/NuevaPersona")
    public String NuevaPersona(HttpServletRequest request, Model model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("profesiones", profesionService.findAll());
        return "persona/formPersona";
    }


    @PostMapping(value = "/eliminar-persona/{id_persona}")
    @ResponseBody
    public void EliminarPersona(HttpServletRequest request, Model model,
            @PathVariable("id_persona") Long id_persona) {
        Persona persona = personaService.findOne(id_persona);
        persona.setEstado_persona("X");
        personaService.save(persona);
    }

    @RequestMapping(value = "/editar-persona/{id_persona}",method = RequestMethod.GET)
    public String editar_r(@PathVariable("id_persona") Long id_persona, Model model) {
        try {
           
            Persona persona = personaService.findOne(id_persona);
            model.addAttribute("persona", persona);

            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("profesiones", profesionService.findAll());
            return "persona/formPersona";

        } catch (Exception e) {

            return "redirect:/adm/Bienvenido";
        }
    }

}
