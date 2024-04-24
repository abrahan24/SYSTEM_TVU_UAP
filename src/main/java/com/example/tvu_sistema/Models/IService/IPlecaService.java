package com.example.tvu_sistema.Models.IService;


import java.sql.Timestamp;
import java.util.List;

import com.example.tvu_sistema.Models.Entity.Pleca;

public interface IPlecaService {
    public List<Pleca> findAll();

	public void save(Pleca pleca);

	public Pleca findOne(Long id);

    public Long insertar_pleca(
        String tema_pleca2,
        String titulo_transmicion_pleca2,
        Timestamp f_pleca2,
        String url_video_pleca2,
        String invitados_pleca2,
        Integer id_programa2
        );

    public List<Pleca> lista_plecas();
}
