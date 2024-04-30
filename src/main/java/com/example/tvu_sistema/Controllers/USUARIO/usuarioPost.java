package com.example.tvu_sistema.Controllers.USUARIO;


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
public class usuarioPost {
    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IProfesionService profesionService;

    @Autowired
	private IUsuarioService usuarioService;

     @Autowired
	private IGeneroService generoService;

    @PostMapping(value = "admin/RegistroUsuarioF")
    public ResponseEntity<String> RegistroUsuarioF(@Validated Usuario usuario,RedirectAttributes flash,HttpServletRequest request, 
   Model model){
        
      
        Usuario usuario2 = (Usuario) request.getSession().getAttribute("usuario");
        
        if (usuario2 != null) {
            if (usuario.getId_usuario() == null) {
                usuarioService.save(usuario);
                return ResponseEntity.ok("Registrar");
            } else {
                
                usuarioService.save(usuario);
                return ResponseEntity.ok("Editar");
            }
               
        }else{
            return ResponseEntity.ok("Error Al Registrar Persona");
        }
    }

    
  
}
