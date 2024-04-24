package com.example.tvu_sistema.Models.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.ICategoria_reportajeDao;
import com.example.tvu_sistema.Models.Entity.Categoria_reportaje;
import com.example.tvu_sistema.Models.IService.ICategoria_reportajeService;

@Service
public class Categoria_reportajeServiceImpl implements ICategoria_reportajeService {
    @Autowired
    private ICategoria_reportajeDao categoria_reportajeDao;

    @Override
    public List<Categoria_reportaje> findAll() {
        // TODO Auto-generated method stub
        return (List<Categoria_reportaje>) categoria_reportajeDao.findAll();
    }

    @Override
    public void save(Categoria_reportaje categoria_reportaje) {
        // TODO Auto-generated method stub
        categoria_reportajeDao.save(categoria_reportaje);
    }

    @Override
    public Categoria_reportaje findOne(Long id) {
        // TODO Auto-generated method stub
        return categoria_reportajeDao.findById(id).orElse(null);
    }
}
