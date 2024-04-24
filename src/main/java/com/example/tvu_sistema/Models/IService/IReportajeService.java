package com.example.tvu_sistema.Models.IService;

import java.sql.Timestamp;
import java.util.List;

import com.example.tvu_sistema.Models.Entity.Reportaje;



public interface IReportajeService {
    public List<Reportaje> findAll();

	public void save(Reportaje reportaje);

	public Reportaje findOne(Long id);

	public Long insertar_reportaje(
        String tema_reportajer,
        String desc_reportajer,
        String lugar_reportajer,
        Timestamp f_reportajer,
        String invitados_reportajer,
        String url_video_reportaje,
        Integer id_programar,
        Integer id_personar,
        Integer id_categoria_reportajer
        );
    
    public List<Reportaje> lista_reportajes();
}
