package com.example.tvu_sistema.Controllers.PLECA;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Pleca;
import com.example.tvu_sistema.Models.Entity.Reportaje;
import com.example.tvu_sistema.Models.IService.IPersonaService;
import com.example.tvu_sistema.Models.IService.IPlecaService;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.Repository.Pleca.PlecaRepository;

@RestController
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
    public String RegistroReportajeF(@Validated Pleca pleca,RedirectAttributes flash,HttpServletRequest request,
    @RequestParam(value = "personas", required = false) Integer[] id_personas,
    @RequestParam(name="f_plecaa",required = false)String f_plecaa
    ) throws ParseException{          
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date f_pleca = formatter.parse(f_plecaa);
        
        Timestamp timestamp = new Timestamp(f_pleca.getTime());

        // Long respuesta = plecaService.insertar_pleca(
        //     pleca.getTema_pleca(), 
        //     pleca.getTitulo_transmicion_pleca(), 
        //     timestamp, 
        //     pleca.getUrl_video_pleca(), 
        //     pleca.getInvitados_pleca(), 
        //     Math.toIntExact(pleca.getPrograma().getId_programa())
        //     );

        // Long respuesta = plecaService.insertar_pleca2(
        // pleca.getTema_pleca(), 
        // pleca.getTitulo_transmicion_pleca(), 
        // timestamp, 
        // pleca.getUrl_video_pleca(), 
        // pleca.getInvitados_pleca(), 
        // Math.toIntExact(pleca.getPrograma().getId_programa()),
        // "3,2"
        // );

        Long respuesta = plecaRepository.insertar_pleca2(
            pleca.getTema_pleca(), 
            pleca.getTitulo_transmicion_pleca(), 
            pleca.getUrl_video_pleca(), 
            pleca.getInvitados_pleca(), 
            timestamp,
            Math.toIntExact(pleca.getPrograma().getId_programa()),
            id_personas
        );

        if (respuesta != 0) {
            return "1";
        }else{
            return "0";
        }        

        //return "0";
    }
}
