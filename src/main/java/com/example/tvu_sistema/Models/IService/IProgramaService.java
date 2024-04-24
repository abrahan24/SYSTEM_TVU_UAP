package com.example.tvu_sistema.Models.IService;

import java.sql.Time;
import java.util.List;

import com.example.tvu_sistema.Models.Entity.Programa;

public interface IProgramaService {
    public List<Programa> findAll();

	public void save(Programa programa);

	public Programa findOne(Long id);   

	public Long insertar_programa(
        String desc_programa, 
        Time hr_empiezo_pogramaP, 
        Time hr_fin_programaP, 
        Integer id_horario, 
        Integer id_persona
        );
    
    public List<Programa> lista_programas();
}
