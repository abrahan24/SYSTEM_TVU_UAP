package com.example.tvu_sistema.Controllers.PERSONA;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Profesion;
import com.example.tvu_sistema.Models.Entity.Tiene;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.ITieneService;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@RestController
public class personaPost {
    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IProfesionService profesionService;
    @Autowired
	private ITieneService tieneService;
    @Autowired
	private IUsuarioService usuarioService;

    @PostMapping(value = "admin/RegistroPersonaF")
    public String RegistroPersonaF(@Validated Persona persona,RedirectAttributes flash,HttpServletRequest request, 
    @RequestParam(name="genero",required = false)Integer id_genero,
    @RequestParam(name="profesiones[]",required = false)Long[] id_profesion
    ){

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		usuario = usuarioService.findOne(usuario.getId_usuario());

        Long resultado = personaService.insertar_persona(
            persona.getNombre(), 
            persona.getAp_paterno(), 
            persona.getAp_materno(), 
            persona.getEdad(), 
            persona.getCi(), 
            persona.getNum_celular(), 
            id_genero);

        if (resultado != 0) {
            for (int i = 0; i < id_profesion.length; i++) {

                Profesion profesion = profesionService.findOne(id_profesion[i]);

                Persona persona2 = personaService.findOne(resultado); // funcion de la BD

                Tiene tiene = new Tiene(); // profesion y la persona

                tiene.setEstado_tiene("A");            
                tiene.setPersona(persona2);
                tiene.setProfesion(profesion);
                tieneService.save(tiene);
            }    
            return "1";
        }else{
            return "0";
        }
    }    
    @PostMapping(value = "RegistroPersona2F")
    public String RegistroPersona2F(@Validated Persona persona,@Validated Usuario usuario, RedirectAttributes flash,HttpServletRequest request,    
    @RequestParam(name="genero",required = false)Integer id_genero,
    @RequestParam(name="profesiones[]",required = false)Long[] id_profesion
    ){

        
        Long respuesta = personaService.insertar_personaUsuario(
        persona.getAp_materno(),
        persona.getAp_paterno(), 
        persona.getCi(), 
        persona.getEdad(), 
        persona.getNombre(), 
        persona.getNum_celular(), 
        id_genero, 
        usuario.getNom_usuario(), 
        usuario.getCorreo(), 
        usuario.getContrasena());

        System.out.println(respuesta);


        if (respuesta != 0) {
            for (int i = 0; i < id_profesion.length; i++) {
                
                Usuario usuario2 = usuarioService.findOne(respuesta);
                Persona persona2 = personaService.findOne(usuario2.getPersona().getId_persona());
                Profesion profesion = profesionService.findOne(id_profesion[i]);

                Tiene tiene = new Tiene(); // profesion y la persona

                tiene.setEstado_tiene("A");
                tiene.setPersona(persona2);
                tiene.setProfesion(profesion);

                tieneService.save(tiene);
            }
            return "1";
        }else{
            return "0";
        }

        

        
    }    
}
