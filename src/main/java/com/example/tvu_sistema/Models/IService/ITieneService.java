package com.example.tvu_sistema.Models.IService;

import java.util.List;

import com.example.tvu_sistema.Models.Entity.Tiene;

public interface ITieneService {
    public List<Tiene> findAll();

	public void save(Tiene tiene);

	public Tiene findOne(Long id);

	public void delete(Long id);
}
