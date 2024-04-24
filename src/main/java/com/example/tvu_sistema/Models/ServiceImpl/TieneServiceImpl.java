package com.example.tvu_sistema.Models.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.ITieneDao;
import com.example.tvu_sistema.Models.Entity.Tiene;
import com.example.tvu_sistema.Models.IService.ITieneService;

@Service
public class TieneServiceImpl implements ITieneService{
    @Autowired
    private ITieneDao tieneDao;

    @Override
    public List<Tiene> findAll() {
        // TODO Auto-generated method stub
        return (List<Tiene>) tieneDao.findAll();
    }

    @Override
    public void save(Tiene tiene) {
        // TODO Auto-generated method stub
        tieneDao.save(tiene);
    }

    @Override
    public Tiene findOne(Long id) {
        // TODO Auto-generated method stub
        return tieneDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        tieneDao.deleteById(id);
    }
}
