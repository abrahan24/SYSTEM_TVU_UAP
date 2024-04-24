package com.example.tvu_sistema.Models.Dao;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tvu_sistema.Models.Entity.Programa;

public interface IProgramaDao extends CrudRepository<Programa, Long>{
    @Query(value = "select * from insertar_programa(?1,?2,?3,?4,?5)", nativeQuery = true)
    public Long insertar_programa(
        String desc_programa, 
        Time hr_empiezo_pogramaP, 
        Time hr_fin_programaP, 
        Integer id_horario, 
        Integer id_persona
        );
    
    @Query(value = "select * from lista_programas()", nativeQuery = true)
    public List<Programa> lista_programas();
}