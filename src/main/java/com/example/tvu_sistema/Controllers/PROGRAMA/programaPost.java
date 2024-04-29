package com.example.tvu_sistema.Controllers.PROGRAMA;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tvu_sistema.Models.Entity.Dias_transmision;
import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.Entity.Profesion;
import com.example.tvu_sistema.Models.Entity.Programa;

import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IDias_transmisionService;

import com.example.tvu_sistema.Models.IService.IProgramaService;



@RestController
public class programaPost {
    @Autowired
    private IProgramaService programaService;

    @Autowired
    private IDias_transmisionService dias_transmisionService;



    @PostMapping(value = "admin/RegistroProgramaF")
    public ResponseEntity<String> RegistroProgramaF(@Validated Programa programa, RedirectAttributes flash,
            HttpServletRequest request,
            @RequestParam(name = "id_dias_transmision[]", required = false) Long[] id_dias_transmision,
            @RequestParam(name = "hr_empiezo_pogramaa", required = false) String hr_empiezo_pograma,
            @RequestParam(name = "hr_fin_programaa", required = false) String hr_fin_programa) throws ParseException {

                System.out.println("ENTRANDO A REGISTRAR");
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Date hr_empiezo_pograma2 = formatter.parse(hr_empiezo_pograma);
        Time hr_empiezo_pograma3 = new Time(hr_empiezo_pograma2.getTime());
        Date hr_fin_programa2 = formatter.parse(hr_fin_programa);
        Time hr_fin_programa3 = new Time(hr_fin_programa2.getTime());
        LocalDate fechaActual = LocalDate.now();
        
        int añoActual = fechaActual.getYear();
        String ano_programa = String.valueOf(añoActual);

        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            
        if (usuario != null) {
            if (programa.getId_programa() == null) {
                programa.setAno_programa(ano_programa);
                programa.setEst_programa("A");
                programa.setHr_empiezo_pograma(hr_empiezo_pograma3);
                programa.setHr_fin_programa(hr_fin_programa3);
                programaService.save(programa);
                return ResponseEntity.ok("Registrar");
            }else{
                programa.setEst_programa("A");
                programa.setHr_empiezo_pograma(hr_empiezo_pograma3);
                programa.setHr_fin_programa(hr_fin_programa3);
                programaService.save(programa);
                return ResponseEntity.ok("Editar");
            }
               
        } else {
            return ResponseEntity.ok("Error Al Registrar el programa");
        }
    }

  
}
