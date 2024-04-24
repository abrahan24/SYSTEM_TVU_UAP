package com.example.tvu_sistema.Models.Repository.ProgramaR;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProgramaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public String anoActual(){
        String sql = "SELECT CAST(EXTRACT(YEAR FROM NOW()) AS VARCHAR) AS current_year;";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

}
