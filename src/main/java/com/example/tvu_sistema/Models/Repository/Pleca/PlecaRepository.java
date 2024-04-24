package com.example.tvu_sistema.Models.Repository.Pleca;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PlecaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Long insertar_pleca2(
        String tema_pleca2,
        String titulo_transmicion_pleca2,
        String url_video_pleca2,
        String invitados_pleca2,
        Timestamp f_pleca2,
        Integer id_programa2,
        Integer[] personas){
            String sql = "select * from insertar_pleca3(?, ?, ?, ?, ? ,?, ?)";
            return jdbcTemplate.queryForObject(sql, Long.class, tema_pleca2, titulo_transmicion_pleca2,  url_video_pleca2, invitados_pleca2, f_pleca2, id_programa2,  personas);
    }
}
