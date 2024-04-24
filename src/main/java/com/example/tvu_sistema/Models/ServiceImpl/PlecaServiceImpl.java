package com.example.tvu_sistema.Models.ServiceImpl;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IPlecaDao;
import com.example.tvu_sistema.Models.Entity.Pleca;
import com.example.tvu_sistema.Models.IService.IPlecaService;

@Service
public class PlecaServiceImpl implements IPlecaService{
    @Autowired
    private IPlecaDao plecaDao;

    @Override
    public List<Pleca> findAll() {
        // TODO Auto-generated method stub
        return (List<Pleca>) plecaDao.findAll();
    }

    @Override
    public void save(Pleca pleca) {
        // TODO Auto-generated method stub
        plecaDao.save(pleca);
    }

    @Override
    public Pleca findOne(Long id) {
        // TODO Auto-generated method stub
        return plecaDao.findById(id).orElse(null);
    }

    @Override
    public Long insertar_pleca(String tema_pleca2, String titulo_transmicion_pleca2, Timestamp f_pleca2,
            String url_video_pleca2, String invitados_pleca2, Integer id_programa2) {
        // TODO Auto-generated method stub
        return plecaDao.insertar_pleca(tema_pleca2, titulo_transmicion_pleca2, f_pleca2, url_video_pleca2, invitados_pleca2, id_programa2);
    }

    @Override
    public List<Pleca> lista_plecas() {
        // TODO Auto-generated method stub
        return (List<Pleca>) plecaDao.lista_plecas();
    }

    
}
