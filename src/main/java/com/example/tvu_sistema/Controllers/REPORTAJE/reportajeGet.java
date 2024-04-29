package com.example.tvu_sistema.Controllers.REPORTAJE;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
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
import com.example.tvu_sistema.Models.Entity.Reportaje;
import com.example.tvu_sistema.Models.IService.ICategoria_reportajeService;
import com.example.tvu_sistema.Models.IService.IDias_transmisionService;
import com.example.tvu_sistema.Models.IService.IGeneroService;
import com.example.tvu_sistema.Models.IService.IHorarioService;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IProfesionService;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.IService.IReportajeService;

import com.example.tvu_sistema.Models.IService.IUsuarioService;
import com.example.tvu_sistema.Models.Otros.Encryptar;
import com.example.tvu_sistema.Models.Repository.ProgramaR.ProgramaRepository;

@Controller
@RequestMapping("/admin")
public class reportajeGet {

    @Autowired
    private IProgramaService programaService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private ICategoria_reportajeService categoria_reportajeService;

    @Autowired
    private IReportajeService reportajeService;

    @Autowired
    private ProgramaRepository programaRepository;

    @RequestMapping(value = "RegistroReportajeA", method = RequestMethod.GET) // Pagina principal
    public String RegistroPersonaV(@Validated Reportaje reportaje, Model model, RedirectAttributes flash,
            HttpServletRequest request) {

        if (request.getSession().getAttribute("persona") != null) {

            List<Reportaje> reportajes = reportajeService.findAll();
           
            model.addAttribute("reportajes", reportajes);

            model.addAttribute("programas", programaService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("categoria_reportajes", categoria_reportajeService.findAll());
            // model.addAttribute("ano_act",  year.toString());    
           

            return "reportaje/registroReportajeA";
        } else {
            return "redirect:/LoginV";
        }
    }

    @PostMapping("/tableReportaje")
    public String tableReportaje(@Validated Reportaje reportaje, Model model, RedirectAttributes flash,
            HttpServletRequest request) throws Exception {

        List<Reportaje> reportajes = reportajeService.findAll();

        model.addAttribute("reportajes", reportajes);

        model.addAttribute("programas", programaService.findAll());
        model.addAttribute("personas", personaService.findAll());
        model.addAttribute("categoria_reportajes", categoria_reportajeService.findAll());

        return "reportaje/tableReportaje";
    }

    @PostMapping(value = "/NuevoReportaje")
    public String NuevoReportaje(HttpServletRequest request, Model model) {
        model.addAttribute("reportaje", new Reportaje());
        model.addAttribute("programas", programaService.findAll());
        model.addAttribute("personas", personaService.findAll());
        model.addAttribute("categoria_reportajes", categoria_reportajeService.findAll());
    
        
        return "reportaje/formReportaje";
    }


     @PostMapping(value = "/eliminar-reportaje/{id_reportaje}")
    @ResponseBody
    public void EliminarReportaje(HttpServletRequest request, Model model,
            @PathVariable("id_reportaje") Long id_reportaje) {
                Reportaje reportaje = reportajeService.findOne(id_reportaje);
                reportaje.setEst_reportaje("X");
                reportajeService.save(reportaje);
             
       
    }

    @RequestMapping(value = "/editar-reportaje/{id_reportaje}")
    public String editar_r(@PathVariable("id_reportaje")  Long id_reportaje, Model model) {
        try {
          
            Reportaje reportaje = reportajeService.findOne(id_reportaje);
            model.addAttribute("reportaje", reportaje);

            model.addAttribute("programas", programaService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("categoria_reportajes", categoria_reportajeService.findAll());
            model.addAttribute("ano_actual", programaRepository.anoActual());

            return "reportaje/formReportaje";

        } catch (Exception e) {

            return "redirect:/adm/Bienvenido";
        }
    }

    @RequestMapping(value = "/RegistroReportaje2F", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String RegistroReportaje2F(@Validated Reportaje reportaje, RedirectAttributes redirectAttrs,
            @RequestParam(name = "f_reportajee", required = false) String f_reportajee) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date f_reportaje = formatter.parse(f_reportajee);

        reportaje.setF_reportaje(f_reportaje);
        reportaje.setEst_reportaje("A");

        reportajeService.save(reportaje);
        return "redirect:/admin/RegistroReportajeA";
    }

    @GetMapping("/modalReportaje/{id_reportaje}")
    public String modalReportaje(@PathVariable("id_reportaje") Long id_reportaje, Model model) {

        Reportaje reportaje = reportajeService.findOne(id_reportaje);
        model.addAttribute("reportaje", reportaje);

        return "reportaje/modalReportaje :: modalContent";
    }

    @GetMapping("/modalReportaje2/{id_reportaje}")
    public String modalReportaje2(@PathVariable("id_reportaje") Long id_reportaje, Model model) {

        Reportaje reportaje = reportajeService.findOne(id_reportaje);
        model.addAttribute("reportaje", reportaje);

        return "reportaje/modalReportaje :: modalContent2";
    }
}
