package com.example.tvu_sistema.Controllers.REPORTAJE;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Reportaje;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.IService.IReportajeService;

@RestController
public class reportajePost {

    @Autowired
	private IProgramaService programaService;

    @Autowired
	private IReportajeService reportajeService;
    
     @PostMapping(value = "admin/RegistroReportajeF")
    public ResponseEntity<String> RegistroReportajeF(@Validated Reportaje reportaje, RedirectAttributes flash,
            HttpServletRequest request,
            @RequestParam(name="f_reportajee",required = false)String f_reportajee
           ) throws ParseException {

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date f_reportaje = formatter.parse(f_reportajee);
            
            Timestamp timestamp = new Timestamp(f_reportaje.getTime());

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            
        if (usuario != null) {
            if (reportaje.getId_reportaje() == null) {
                reportaje.setEst_reportaje("A");
                reportaje.setF_reportaje(f_reportaje);
                reportajeService.save(reportaje);
                return ResponseEntity.ok("Registrar");
            }else{
                reportaje.setEst_reportaje("A");
                reportaje.setF_reportaje(f_reportaje);
                reportajeService.save(reportaje);
                return ResponseEntity.ok("Editar");
            }
               
        } else {
            return ResponseEntity.ok("Error Al Registrar Reportaje");
        }
    }
   
}
