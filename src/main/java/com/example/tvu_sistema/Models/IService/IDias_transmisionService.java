package com.example.tvu_sistema.Models.IService;

import java.util.List;

import com.example.tvu_sistema.Models.Entity.Dias_transmision;


public interface IDias_transmisionService {
    public List<Dias_transmision> findAll();

	public void save(Dias_transmision dias_transmision);

	public Dias_transmision findOne(Long id);
}
