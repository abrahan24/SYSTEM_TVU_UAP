package com.example.tvu_sistema.Models.IService;

import java.util.List;

import com.example.tvu_sistema.Models.Entity.Horario;

public interface IHorarioService {
    public List<Horario> findAll();

	public void save(Horario horario);

	public Horario findOne(Long id);
}
