package com.example.tvu_sistema.Controllers.LOGIN;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IGeneroService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Controller
public class login3 {
    @Autowired
	private IUsuarioService usuarioService;
    @Autowired
	private IGeneroService generoService;
    @Autowired
	private IProfesionService profesionService;

    @RequestMapping(value = "Login2V", method = RequestMethod.GET) // Pagina principal
    public String LoginV(HttpServletRequest request,RedirectAttributes flash, Model model,@Validated Persona persona, @Validated Usuario usuario) {

        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("profesiones", profesionService.findAll());

        return "login/login2A";
    }
}
