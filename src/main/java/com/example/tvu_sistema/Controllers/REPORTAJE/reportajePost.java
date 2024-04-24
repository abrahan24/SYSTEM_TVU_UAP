package com.example.tvu_sistema.Controllers.REPORTAJE;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Dias_transmision;
import com.example.tvu_sistema.Models.Entity.Programa;
import com.example.tvu_sistema.Models.Entity.Reportaje;
import com.example.tvu_sistema.Models.Entity.Transmite;
import com.example.tvu_sistema.Models.IService.IProgramaService;
import com.example.tvu_sistema.Models.IService.IReportajeService;

@RestController
public class reportajePost {

    @Autowired
	private IProgramaService programaService;

    @Autowired
	private IReportajeService reportajeService;
    

    @PostMapping(value = "RegistroReportajeF")
    public String RegistroReportajeF(@Validated Reportaje reportaje,RedirectAttributes flash,HttpServletRequest request,
    @RequestParam(name="f_reportajee",required = false)String f_reportajee
    ) throws ParseException{          
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date f_reportaje = formatter.parse(f_reportajee);
        
        Timestamp timestamp = new Timestamp(f_reportaje.getTime());

        Long respuesta = reportajeService.insertar_reportaje(
            reportaje.getTema_reportaje(), 
            reportaje.getDesc_reportaje(),
            reportaje.getLugar_reportaje(), 
            timestamp, 
            reportaje.getInvitados_reportaje(),
            reportaje.getUrl_video_reportaje(),
            Math. toIntExact(reportaje.getPrograma().getId_programa()),
            Math. toIntExact(reportaje.getPersona().getId_persona()),
            Math. toIntExact(reportaje.getCategoria_reportaje().getId_categoria_reportaje())
            );

        if (respuesta != 0) {
            return "1";
        }else{
            return "0";
        }        
    }
}
