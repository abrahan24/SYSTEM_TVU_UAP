package com.example.tvu_sistema.Models.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IProfesionDao;
import com.example.tvu_sistema.Models.Entity.Profesion;
import com.example.tvu_sistema.Models.IService.IProfesionService;

@Service
public class ProfesionServiceImpl implements IProfesionService{
    @Autowired
    private IProfesionDao profesionDao;

    @Override
    public List<Profesion> findAll() {
        // TODO Auto-generated method stub
        return (List<Profesion>) profesionDao.findAll();
    }

    @Override
    public void save(Profesion profesion) {
        // TODO Auto-generated method stub
        profesionDao.save(profesion);
    }

    @Override
    public Profesion findOne(Long id) {
        // TODO Auto-generated method stub
        return profesionDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        profesionDao.deleteById(id);
    }
}