package com.example.tvu_sistema.Controllers.PLECA;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Pleca;
import com.example.tvu_sistema.Models.Entity.Reportaje;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IPlecaService;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.Otros.Encryptar;
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

            List<Pleca> plecas = plecaService.lista_plecas();
            List<String> encryptedIds = new ArrayList<>();
            for (Pleca pleca2 : plecas) {
                try {
                    String id_encryptado = Encryptar.encrypt(Long.toString(pleca2.getId_pleca()));
                    encryptedIds.add(id_encryptado);    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e);
                }
                
            }
            model.addAttribute("plecas", plecas);
            model.addAttribute("id_encryptado", encryptedIds);


            model.addAttribute("programas", programaService.findAll());
            model.addAttribute("personas", personaService.findAll());        
            model.addAttribute("ano_actual", programaRepository.anoActual());    
                        
            return "pleca/registroPlecaA";
        }else{
            return "redirect:/LoginV";
        }
    }

    @GetMapping("/tablePleca")
    public String tablePleca(@Validated Pleca pleca, Model model,RedirectAttributes flash,HttpServletRequest request) throws Exception {

        List<Pleca> plecas = plecaService.lista_plecas();
        List<String> encryptedIds = new ArrayList<>();
        for (Pleca pleca2 : plecas) {
            try {
                String id_encryptado = Encryptar.encrypt(Long.toString(pleca2.getId_pleca()));
                encryptedIds.add(id_encryptado);    
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e);
            }
            
        }
        model.addAttribute("plecas", plecas);
        model.addAttribute("id_encryptado", encryptedIds);


        model.addAttribute("programas", programaService.findAll());
        model.addAttribute("personas", personaService.findAll());
        
        return "pleca/tablePleca :: table";
    }

    @RequestMapping(value = "/eliminar-pleca/{id_pleca}")
       public String eliminar_r(@PathVariable("id_pleca") String id_pleca) throws Exception {
        try {
            Long id_ple = Long.parseLong(Encryptar.decrypt(id_pleca));
            Pleca pleca = plecaService.findOne(id_ple);
            pleca.setEst_pleca("X");
            plecaService.save(pleca);
            return "redirect:/admin/RegistroPlecaA";

        } catch (Exception e) {
            return "redirect:/admin/BienvenidoV";
        }
    }

    @RequestMapping(value = "/editar-pleca/{id_pleca}")
    public String editar_r(@PathVariable("id_pleca") String id_pleca, Model model) {
        try {
            Long id_ple = Long.parseLong(Encryptar.decrypt(id_pleca));
            Pleca pleca = plecaService.findOne(id_ple);
            model.addAttribute("pleca", pleca);

            List<Pleca> plecas = plecaService.lista_plecas();
            List<String> encryptedIds = new ArrayList<>();
            for (Pleca pleca2 : plecas) {
                try {
                    String id_encryptado = Encryptar.encrypt(Long.toString(pleca2.getId_pleca()));
                    encryptedIds.add(id_encryptado);    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e);
                }
                
            }
            model.addAttribute("plecas", plecas);
            model.addAttribute("id_encryptado", encryptedIds);


            model.addAttribute("programas", programaService.findAll());
            model.addAttribute("personas", personaService.findAll());
            model.addAttribute("ano_actual", programaRepository.anoActual());

            return "pleca/registroPlecaA";

        } catch (Exception e) {

            return "redirect:/adm/Bienvenido";
        }
    }

    @RequestMapping(value = "/RegistroPleca2F", method = RequestMethod.POST) // Enviar datos de Registro a Lista
    public String RegistroPleca2F(@Validated Pleca pleca, RedirectAttributes redirectAttrs,
    @RequestParam(value = "personas", required = false) Integer[] id_personas,
    @RequestParam(name="f_plecaa",required = false)String f_plecaa
    ) throws ParseException {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date f_pleca = formatter.parse(f_plecaa);
        
        pleca.setF_pleca(f_pleca);
        pleca.setEst_pleca("A");

        plecaService.save(pleca);
        return "redirect:/admin/RegistroPlecaA";
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
