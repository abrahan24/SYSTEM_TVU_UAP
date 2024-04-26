package com.example.tvu_sistema.Controllers.PERSONA;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Profesion;
import com.example.tvu_sistema.Models.Entity.Tiene;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.ITieneService;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Controller
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
    public ResponseEntity<String> RegistroPersonaF(@Validated Persona persona,RedirectAttributes flash,HttpServletRequest request, 
    @RequestParam(name="genero",required = false)Integer id_genero,
    @RequestParam(name="profesiones",required = false)Long[] id_profesion
    ){

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (usuario != null) {
            if (persona.getId_persona() == null) {
                persona.setEstado_persona("A");
                personaService.save(persona);
                for (int i = 0; i < id_profesion.length; i++) {

                    Profesion profesion = profesionService.findOne(id_profesion[i]);

                    Tiene tiene = new Tiene(); // profesion y la persona

                    tiene.setEstado_tiene("A");            
                    tiene.setPersona(persona);
                    tiene.setProfesion(profesion);
                    tieneService.save(tiene);
                } 

                return ResponseEntity.ok("Se realizó el registro correctamente");
            } else {
                
                personaService.save(persona);
                return ResponseEntity.ok("Se modifico el registro correctamente");
            }
               
        }else{
            return ResponseEntity.ok("Error Al Registrar Persona");
        }
    }

    // @PostMapping(value = "/ModPersonaG")
    // @ResponseBody
    // public ResponseEntity<String> ModUsuarioG(@Validated Persona persona, Model model) {
    //     Persona persona2 = personaService.findOne(persona.getId_persona());

    //     List<Persona> listapers = personaService.findAll();
    //     System.out.println("el limite de la lista es: " + listapers.size());
    //     for (int i = 0; i < listapers.size(); i++) {
    //         if (listapers.get(i).getCi() == persona2.getCi()) {
    //             listapers.remove(i);
    //             break;
    //         }
    //     }
    //     System.out.println("el nuevo limite de la lista es: " + listapers.size());
    //     int cont = 0;
    //     for (int i = 0; i < listapers.size(); i++) {
    //         if (listapers.get(i).getCi().equals(persona.getCi())) {
    //             cont++;
    //             break;
    //         }
    //     }
    //     System.out.println("la variable cont: " + cont);
    //     if (cont == 0) {
    //         persona.setFechaRegistro(persona2.getFechaRegistro());
    //         persona.setHoraRegistro(persona2.getHoraRegistro());
    //         personaService.save(persona);
    //         return ResponseEntity.ok("Se modificó el registro correctamente");
    //     } else {
    //         return ResponseEntity.ok("Ya existe un registro con este C.I.");
    //     }
    // }
    

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
