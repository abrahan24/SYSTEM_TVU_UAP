package com.example.tvu_sistema.Models.IService;
import java.util.List;

import com.example.tvu_sistema.Models.Entity.Genero;


public interface IGeneroService {
    public List<Genero> findAll();

	public void save(Genero genero);

	public Genero findOne(Long id);
}