package com.example.tvu_sistema.Models.ServiceImpl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IReportajeDao;
import com.example.tvu_sistema.Models.Entity.Reportaje;
import com.example.tvu_sistema.Models.IService.IReportajeService;

@Service
public class ReportajeServiceImpl implements IReportajeService{
    
    @Autowired
    private IReportajeDao reportajeDao;

    @Override
    public List<Reportaje> findAll() {
        // TODO Auto-generated method stub
        return (List<Reportaje>) reportajeDao.findAll();
    }

    @Override
    public void save(Reportaje reportaje) {
        // TODO Auto-generated method stub
        reportajeDao.save(reportaje);
    }

    @Override
    public Reportaje findOne(Long id) {
        // TODO Auto-generated method stub
        return reportajeDao.findById(id).orElse(null);
    }

    @Override
    public Long insertar_reportaje(String tema_reportajer, String desc_reportajer, String lugar_reportajer,
            Timestamp f_reportajer, String invitados_reportajer, String url_video_reportaje, Integer id_programar,
            Integer id_personar, Integer id_categoria_reportajer) {
        // TODO Auto-generated method stub
        return reportajeDao.insertar_reportaje(tema_reportajer, desc_reportajer, lugar_reportajer, f_reportajer, invitados_reportajer, url_video_reportaje, id_programar, id_personar, id_categoria_reportajer);
    }

    @Override
    public List<Reportaje> lista_reportajes() {
        // TODO Auto-generated method stub
        return (List<Reportaje>) reportajeDao.lista_reportajes();
    }

    

    


    
}
