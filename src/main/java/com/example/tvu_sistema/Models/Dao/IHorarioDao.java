package com.example.tvu_sistema.Models.Dao;

import org.springframework.data.repository.CrudRepository;

import com.example.tvu_sistema.Models.Entity.Horario;

public interface IHorarioDao extends CrudRepository<Horario, Long>{
    
}
