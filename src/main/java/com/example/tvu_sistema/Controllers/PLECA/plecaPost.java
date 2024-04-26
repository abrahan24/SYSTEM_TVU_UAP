package com.example.tvu_sistema.Controllers.PLECA;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Pleca;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IPlecaService;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.Repository.Pleca.PlecaRepository;

@Controller
public class plecaPost {
    
    @Autowired
	private IProgramaService programaService;

    @Autowired
	private IPlecaService plecaService;

    @Autowired
	private IPersonaService personaService;

    @Autowired
    private PlecaRepository plecaRepository;

    @PostMapping(value = "RegistroPlecaF")
    public ResponseEntity<String> RegistroReportajeF(@Validated Pleca pleca,RedirectAttributes flash,HttpServletRequest request,
    @RequestParam(value = "personas", required = false) Integer[] id_personas
    ) throws ParseException{          
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        
        if (usuario != null) {
            if (pleca.getId_pleca() == null) {
                pleca.setEst_pleca("A");
                plecaService.save(pleca);
                return ResponseEntity.ok("Registrar");
            }else{
                pleca.setEst_pleca("A");
                plecaService.save(pleca);
                return ResponseEntity.ok("Editar");
            }
        } else {
            return ResponseEntity.ok("Error al Registrar");
        }
        
    }
}
