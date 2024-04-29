package com.example.tvu_sistema.Controllers.PROGRAMA;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Programa;
import com.example.tvu_sistema.Models.IService.IDias_transmisionService;
import com.example.tvu_sistema.Models.IService.IHorarioService;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProgramaService;

import com.example.tvu_sistema.Models.Otros.Encryptar;
import com.example.tvu_sistema.Models.Repository.ProgramaR.ProgramaRepository;

@Controller
@RequestMapping("/admin")
public class programaGet {

    @Autowired
	private IPersonaService personaService;
    @Autowired
	private IHorarioService horarioService;
    @Autowired
    private IDias_transmisionService dias_transmisionService;
    @Autowired
    private IProgramaService programaService;
 
    @Autowired
    private ProgramaRepository programaRepository;
    
    @RequestMapping(value = "RegistroProgramaA", method = RequestMethod.GET) // Pagina principal
    public String RegistroPersonaV(@Validated Programa programa, Model model,RedirectAttributes flash,HttpServletRequest request) {

        if (request.getSession().getAttribute("persona") != null) {

            List<Programa> programas = programaService.findAll();
            
       
            model.addAttribute("programas", programas);
  
            model.addAttribute("horarios", horarioService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("dias_transmisiones", dias_transmisionService.findAll());
      
            model.addAttribute("ano_actual", programaRepository.anoActual());
                        
            return "programa/registroProgramaA";
        }else{
            return "redirect:/LoginV";
        }
    }

    @PostMapping("/tablePrograma")
    public String tablePrograma(@Validated Programa programa, Model model,RedirectAttributes flash,HttpServletRequest request) throws Exception {

        List<Programa> programas = programaService.findAll();
      
        model.addAttribute("programas", programas);
     

        model.addAttribute("horarios", horarioService.findAll());
        model.addAttribute("personas", personaService.findAll());
        model.addAttribute("dias_transmisiones", dias_transmisionService.findAll());

        
        return "programa/tablePrograma";
    }

        @PostMapping(value = "/NuevoPrograma")
    public String NuevoPrograma(HttpServletRequest request, Model model) {
        model.addAttribute("programa", new Programa());
        model.addAttribute("horarios", horarioService.findAll());
        model.addAttribute("personas", personaService.findAll());
        model.addAttribute("dias_transmisiones", dias_transmisionService.findAll());

        return "programa/formPrograma";
    }

     @PostMapping(value = "/eliminar-programa/{id_programa}")
    @ResponseBody
    public void EliminarPrograma(HttpServletRequest request, Model model,
            @PathVariable("id_programa") Long id_programa) {
                Programa programa = programaService.findOne(id_programa);
                programa.setEst_programa("X");
                programaService.save(programa);
       
    }

    @RequestMapping(value = "/editar-programa/{id_programa}")
    public String editar_r(@PathVariable("id_programa") Long id_programa, Model model) {
        try {
       

            Programa programa = programaService.findOne(id_programa);
            model.addAttribute("programa", programa);

            model.addAttribute("hr_empiezo_pogramaa", programa.getHr_empiezo_pograma());
            model.addAttribute("hr_fin_programaa", programa.getHr_fin_programa());

         
          
            model.addAttribute("horarios", horarioService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("dias_transmisiones", dias_transmisionService.findAll());

            model.addAttribute("ano_actual", programaRepository.anoActual());

            return "programa/formPrograma";

        } catch (Exception e) {

            return "redirect:/adm/Bienvenido";
        }
    }

    @RequestMapping(value = "/RegistroPrograma2F", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String RegistroPrograma2F(@Validated Programa programa, RedirectAttributes redirectAttrs,
    @RequestParam(name="hr_empiezo_pogramaa",required = false)String hr_empiezo_pograma,
    @RequestParam(name="hr_fin_programaa",required = false)String hr_fin_programa
    ) throws ParseException { // validar los datos capturados (1)
        
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date hr_empiezo_pograma2 = formatter.parse(hr_empiezo_pograma);
        Time hr_empiezo_pograma3 = new Time(hr_empiezo_pograma2.getTime());
        Date hr_fin_programa2 = formatter.parse(hr_fin_programa);
        Time hr_fin_programa3 = new Time(hr_fin_programa2.getTime());

        programa.setHr_empiezo_pograma(hr_empiezo_pograma3);
        programa.setHr_fin_programa(hr_fin_programa3);
        programa.setEst_programa("A");

        programaService.save(programa);
        return "redirect:/admin/RegistroProgramaA";
    }
}
