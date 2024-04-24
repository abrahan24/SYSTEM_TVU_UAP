package com.example.tvu_sistema.Models.Dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tvu_sistema.Models.Entity.Programa;
import com.example.tvu_sistema.Models.Entity.Reportaje;

public interface IReportajeDao extends CrudRepository<Reportaje, Long>{
    

    @Query(value = "select * from insertar_reportaje(?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
    public Long insertar_reportaje(
        String tema_reportajer,
        String desc_reportajer,
        String lugar_reportajer,
        Timestamp f_reportajer,
        String invitados_reportajer,
        String url_video_reportaje,
        Integer id_programar,
        Integer id_personar,
        Integer id_categoria_reportajer
        );
    
    @Query(value = "select * from lista_reportajes()", nativeQuery = true)
    public List<Reportaje> lista_reportajes();
    
}
