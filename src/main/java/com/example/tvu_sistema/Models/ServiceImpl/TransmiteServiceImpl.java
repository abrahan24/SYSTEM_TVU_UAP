package com.example.tvu_sistema.Models.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.ITransmiteDao;
import com.example.tvu_sistema.Models.Entity.Transmite;
import com.example.tvu_sistema.Models.IService.ITransmiteService;


@Service
public class TransmiteServiceImpl implements ITransmiteService{
    @Autowired
    private ITransmiteDao transmiteDao;

    @Override
    public List<Transmite> findAll() {
        // TODO Auto-generated method stub
        return (List<Transmite>) transmiteDao.findAll();
    }

    @Override
    public void save(Transmite transmite) {
        // TODO Auto-generated method stub
        transmiteDao.save(transmite);
    }

    @Override
    public Transmite findOne(Long id) {
        // TODO Auto-generated method stub
        return transmiteDao.findById(id).orElse(null);
    }
    
}
