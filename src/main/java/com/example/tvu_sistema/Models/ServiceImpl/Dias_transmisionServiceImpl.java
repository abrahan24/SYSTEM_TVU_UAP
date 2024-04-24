package com.example.tvu_sistema.Models.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IDias_transmisionDao;
import com.example.tvu_sistema.Models.Entity.Dias_transmision;
import com.example.tvu_sistema.Models.IService.IDias_transmisionService;

@Service
public class Dias_transmisionServiceImpl implements IDias_transmisionService{
    
    @Autowired
    private IDias_transmisionDao dias_transmisionDao;

    @Override
    public List<Dias_transmision> findAll() {
        // TODO Auto-generated method stub
        return (List<Dias_transmision>) dias_transmisionDao.findAll();
    }

    @Override
    public void save(Dias_transmision dias_transmision) {
        // TODO Auto-generated method stub
        dias_transmisionDao.save(dias_transmision);
    }

    @Override
    public Dias_transmision findOne(Long id) {
        // TODO Auto-generated method stub
        return dias_transmisionDao.findById(id).orElse(null);
    }


}
