package com.example.tvu_sistema.Models.IService;

import java.util.List;

import com.example.tvu_sistema.Models.Entity.Categoria_reportaje;

public interface ICategoria_reportajeService {
    public List<Categoria_reportaje> findAll();

	public void save(Categoria_reportaje categoria_reportaje);

	public Categoria_reportaje findOne(Long id);
}
