package com.example.tvu_sistema.Controllers.PERSONA;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Profesion;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IGeneroService;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Controller
public class personaPost {
    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IProfesionService profesionService;

    @Autowired
	private IUsuarioService usuarioService;

     @Autowired
	private IGeneroService generoService;

    @PostMapping(value = "admin/RegistroPersonaF")
    public ResponseEntity<String> RegistroPersonaF(@Validated Persona persona,RedirectAttributes flash,HttpServletRequest request, 
    @RequestParam(name="genero",required = false)Integer id_genero,
    @RequestParam(name="profesiones",required = false)Long[] id_profesion
    , Model model){
        Persona persona2 = personaService.getPersonaCI(persona.getCi());
      
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (usuario != null) {
            if (persona.getId_persona() == null) {
                if (persona2 != null) {
          
                    return ResponseEntity.ok("existe"); 
                }
                persona.setEstado_persona("A");
                personaService.save(persona);
                
                return ResponseEntity.ok("Registrar");
            } else {
                persona.setEstado_persona("A");
                personaService.save(persona);
                return ResponseEntity.ok("Editar");
            }
               
        }else{
            return ResponseEntity.ok("Error Al Registrar Persona");
        }
    }


      @PostMapping(value = "RegistroPublicoPersona")
      public ResponseEntity<String> RegistroPublicoPersona(@Validated Persona persona, @Validated Usuario usuario, RedirectAttributes flash,
            HttpServletRequest request,
            @RequestParam(name = "profesiones[]", required = false) Long[] id_profesion
            , @RequestParam(name = "ci") String ci,Model model) {

                Persona persona2 = personaService.getPersonaCI(ci);
                if (persona2 != null) {
                
                    model.addAttribute("generos", generoService.findAll());
                    model.addAttribute("profesiones", profesionService.findAll());
                    return ResponseEntity.ok("error"); 
                }

                persona.setEstado_persona("A");
                personaService.save(persona);     

                usuario.setEst_usuario("P");
                usuario.setPersona(persona);
                usuarioService.save(usuario);

               

                return ResponseEntity.ok("exito");

    }

    
  
}
