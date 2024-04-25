package com.example.tvu_sistema.Controllers.PROGRAMA;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Dias_transmision;

import com.example.tvu_sistema.Models.Entity.Programa;

import com.example.tvu_sistema.Models.Entity.Transmite;

import com.example.tvu_sistema.Models.IService.IDias_transmisionService;

import com.example.tvu_sistema.Models.IService.IProgramaService;

import com.example.tvu_sistema.Models.IService.ITransmiteService;

@RestController
public class programaPost {
    @Autowired
	private IProgramaService programaService;

    @Autowired
	private IDias_transmisionService dias_transmisionService;

    @Autowired
	private ITransmiteService transmiteService;
    
    @PostMapping(value = "RegistroProgramaF")
    public String RegistroProgramaF(@Validated Programa programa,RedirectAttributes flash,HttpServletRequest request,
    @RequestParam(name="id_dias_transmision[]",required = false)Long[] id_dias_transmision,
    @RequestParam(name="hr_empiezo_pogramaa",required = false)String hr_empiezo_pograma,
    @RequestParam(name="hr_fin_programaa",required = false)String hr_fin_programa
    ) throws ParseException{  

        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date hr_empiezo_pograma2 = formatter.parse(hr_empiezo_pograma);
        Time hr_empiezo_pograma3 = new Time(hr_empiezo_pograma2.getTime());
        Date hr_fin_programa2 = formatter.parse(hr_fin_programa);
        Time hr_fin_programa3 = new Time(hr_fin_programa2.getTime());


        Long respuesta = programaService.insertar_programa(
            programa.getDesc_programa(), 
            hr_empiezo_pograma3, 
            hr_fin_programa3, 
            Math.toIntExact(programa.getHorario().getId_horario()), 
            Math.toIntExact(programa.getPersona().getId_persona())
            );
        
        if (respuesta != 0) {
            for (int i = 0; i < id_dias_transmision.length; i++) {
                Programa programa2 = programaService.findOne(respuesta);
    
                Dias_transmision dias_transmisiones = dias_transmisionService.findOne(id_dias_transmision[i]);
    
                Transmite transmite = new Transmite();
                transmite.setDias_transmision(dias_transmisiones);
                transmite.setPrograma(programa2);
                transmite.setEst_transmite("A");                
                transmiteService.save(transmite);
            }

            return "1";
        }
        

        return "0";
    }   
}
