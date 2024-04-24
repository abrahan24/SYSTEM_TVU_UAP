package com.example.tvu_sistema.Models.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IGeneroDao;
import com.example.tvu_sistema.Models.Entity.Genero;
import com.example.tvu_sistema.Models.IService.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService{
    @Autowired
    private IGeneroDao generoDao;

    @Override
    public List<Genero> findAll() {
        // TODO Auto-generated method stub
        return (List<Genero>) generoDao.findAll();
    }

    @Override
    public void save(Genero genero) {
        // TODO Auto-generated method stub
        generoDao.save(genero);
    }

    @Override
    public Genero findOne(Long id) {
        // TODO Auto-generated method stub
        return generoDao.findById(id).orElse(null);
    }
}