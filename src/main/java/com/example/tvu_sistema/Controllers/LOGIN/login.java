package com.example.tvu_sistema.Controllers.LOGIN;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class login {

    @RequestMapping(value = "/", method = RequestMethod.GET) // Pagina principal
    public String empty() {

        
        return "redirect:/LoginV";
    }

    @RequestMapping(value = "LoginV", method = RequestMethod.GET) // Pagina principal
    public String LoginV(HttpServletRequest request,RedirectAttributes flash,@RequestParam(name="mensaje",required = false)String mensaje, Model model) {

        if (mensaje!=null) {
            model.addAttribute("mensaje" , mensaje);
        }
        return "login/loginA";
    }

    @RequestMapping(value = "/admin/BienvenidoV", method = RequestMethod.GET) // Pagina principal
    public String BienvenidoV(HttpServletRequest request,RedirectAttributes flash,@RequestParam(name="mensaje",required = false)String mensaje, Model model) {

        if (request.getSession().getAttribute("persona") != null) {


            return "login/bienvenidoV";
        }else{
            return "redirect:/LoginV";
        }
    }

    @RequestMapping(value = "/admin/inicio", method = RequestMethod.GET) // Pagina principal
    public String Bienvenido(HttpServletRequest request,RedirectAttributes flash,@RequestParam(name="mensaje",required = false)String mensaje, Model model) {

        if (request.getSession().getAttribute("usuario") != null) {


            return "login/bienvenidoV";
        }else{
            return "redirect:/LoginV";
        }
    }

    @RequestMapping("/cerrar_sesion")
    public String cerrarSesion(HttpServletRequest request, RedirectAttributes flash){
        HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
			flash.addAttribute("mensaje", "Se cerro sesion con exito!");
		}
		return "redirect:/LoginV";
    }
}
