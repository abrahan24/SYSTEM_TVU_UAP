package com.example.tvu_sistema.Controllers.LOGIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Controller
public class login2 {
     @Autowired
	private IUsuarioService usuarioService;

   // Funciòn de iniciar sesiòn administrador
	@RequestMapping(value = "/LogearseF", method = RequestMethod.POST)
	public String logearseF(@RequestParam(value = "usuario") String user,
			@RequestParam(value = "contrasena") String contrasena, Model model, HttpServletRequest request,
			RedirectAttributes flash) {
		// los dos parametros de usuario, contraseña vienen del formulario html
		Usuario usuario = usuarioService.getUsuarioContraseña(user, contrasena);

		if (usuario != null) {
			if (usuario.getEst_usuario().equals("C")) {
				return "redirect:/cerrar_sesionAdm";
			}
			HttpSession sessionAdministrador = request.getSession(false);
			
			sessionAdministrador = request.getSession(true);
			sessionAdministrador.setAttribute("usuario", usuario);
			sessionAdministrador.setAttribute("persona", usuario.getPersona());

			flash.addAttribute("success", usuario.getPersona().getNombre());

			
			return "redirect:/admin/inicio";
			
		} else {
			return "redirect:/LoginV";
		}

	}
}
