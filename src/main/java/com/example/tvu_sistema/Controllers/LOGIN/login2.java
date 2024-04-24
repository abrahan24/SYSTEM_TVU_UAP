package com.example.tvu_sistema.Controllers.LOGIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@RestController
public class login2 {
     @Autowired
	private IUsuarioService usuarioService;

    @PostMapping(value = "LoginF") // Pagina principal
    public String LoginF(HttpServletRequest request,RedirectAttributes flash,
    @RequestParam(value = "usuario") String usuario,
    @RequestParam(value = "contrasena") String contrasena
    ) {
            Integer a = usuarioService.validar_usuario(usuario, contrasena);
            
            if (a != 0) {
                Usuario usuario2 = usuarioService.findOne(Long.valueOf(usuarioService.validar_usuario(usuario, contrasena)));

                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario2);
                session.setAttribute("persona", usuario2.getPersona());
                return "1";
            }
            return "0";
    }
}
