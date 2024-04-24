package com.example.tvu_sistema.Models.Dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tvu_sistema.Models.Entity.Pleca;

public interface IPlecaDao extends CrudRepository<Pleca, Long>{
    @Query(value = "select * from insertar_pleca(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    public Long insertar_pleca(
        String tema_pleca2,
        String titulo_transmicion_pleca2,
        Timestamp f_pleca2,
        String url_video_pleca2,
        String invitados_pleca2,
        Integer id_programa2
        );
    
    
    
    @Query(value = "select * from lista_plecas()", nativeQuery = true)
    public List<Pleca> lista_plecas();
}
