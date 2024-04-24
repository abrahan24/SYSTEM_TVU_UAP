package com.example.tvu_sistema.Models.IService;

import java.util.List;

import com.example.tvu_sistema.Models.Entity.Transmite;

public interface ITransmiteService {
    public List<Transmite> findAll();

	public void save(Transmite transmite);

	public Transmite findOne(Long id);
}
