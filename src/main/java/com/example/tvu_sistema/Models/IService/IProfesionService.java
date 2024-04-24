package com.example.tvu_sistema.Models.IService;
import java.util.List;

import com.example.tvu_sistema.Models.Entity.Profesion;

public interface IProfesionService {
    public List<Profesion> findAll();

	public void save(Profesion profesion);

	public Profesion findOne(Long id);

    public void delete(Long id);
}