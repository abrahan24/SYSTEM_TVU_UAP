package com.example.tvu_sistema.Controllers.PLECA;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Pleca;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IPlecaService;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.Repository.ProgramaR.ProgramaRepository;

@Controller
@RequestMapping("/admin")
public class plecaGet {
    @Autowired
	private IProgramaService programaService;

    @Autowired
	private IPersonaService personaService;

    @Autowired
	private IPlecaService plecaService;

    @Autowired
	private ProgramaRepository programaRepository;

    @RequestMapping(value = "RegistroPlecaA", method = RequestMethod.GET) // Pagina principal
    public String RegistroPlecaV( @Validated Pleca pleca, Model model,RedirectAttributes flash,HttpServletRequest request) {

        if (request.getSession().getAttribute("persona") != null) {

            model.addAttribute("programas", programaService.findAll());
            model.addAttribute("personas", personaService.findAll());        
            model.addAttribute("ano_actual", programaRepository.anoActual());    
                        
            return "pleca/registroPlecaA";
        }else{
            return "redirect:/LoginV";
        }
    }

    @PostMapping("/tablePleca")
    public String tablePleca(@Validated Pleca pleca, Model model,RedirectAttributes flash,HttpServletRequest request) throws Exception {

        model.addAttribute("programas", programaService.findAll());
        model.addAttribute("personas", personaService.findAll());
        model.addAttribute("plecas", plecaService.findAll());
        return "pleca/tablePleca";
    }

     @PostMapping(value = "/NuevaPleca")
    public String NuevaPersona(HttpServletRequest request, Model model) {
        model.addAttribute("pleca", new Pleca());
        model.addAttribute("programas", programaService.findAll());
        model.addAttribute("personas", personaService.findAll());
        model.addAttribute("ano_actual", programaRepository.anoActual());
        return "pleca/formPleca";
    }


    @PostMapping(value = "/eliminar-placa/{id_pleca}")
    @ResponseBody
    public void EliminarPersona(HttpServletRequest request, Model model,
            @PathVariable("id_pleca") Long id_pleca) {
        Pleca pleca = plecaService.findOne(id_pleca);
        pleca.setEst_pleca("X");
        plecaService.save(pleca);
    }

    @RequestMapping(value = "/editar-pleca/{id_pleca}",method = RequestMethod.GET)
    public String editar_r(@PathVariable("id_pleca") Long id_pleca, Model model) {
        try {
            Pleca pleca = plecaService.findOne(id_pleca);
            model.addAttribute("pleca", pleca);

            model.addAttribute("programas", programaService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("ano_actual", programaRepository.anoActual());

            return "pleca/formPleca";

        } catch (Exception e) {

            return "redirect:/adm/Bienvenido";
        }
    }


    @GetMapping("/modalPleca/{id_pleca}")
    public String modalPleca(@PathVariable("id_pleca") Long id_pleca,Model model) {
        
        Pleca pleca = plecaService.findOne(id_pleca);
        model.addAttribute("pleca", pleca);

        return "pleca/modalPleca :: modalContent";
    }

    @GetMapping("/modalPleca2/{id_pleca}")
    public String modalPleca2(@PathVariable("id_pleca") Long id_pleca,Model model) {
        
        Pleca pleca = plecaService.findOne(id_pleca);
        model.addAttribute("pleca", pleca);

        return "pleca/modalPleca :: modalContent2";
    }
}
