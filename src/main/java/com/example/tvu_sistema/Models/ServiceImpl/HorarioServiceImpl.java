package com.example.tvu_sistema.Models.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IHorarioDao;
import com.example.tvu_sistema.Models.Entity.Horario;
import com.example.tvu_sistema.Models.IService.IHorarioService;

@Service
public class HorarioServiceImpl implements IHorarioService{
    
    @Autowired
    private IHorarioDao horarioDao;

    @Override
    public List<Horario> findAll() {
        // TODO Auto-generated method stub
        return (List<Horario>) horarioDao.findAll();
    }

    @Override
    public void save(Horario horario) {
        // TODO Auto-generated method stub
        horarioDao.save(horario);
    }

    @Override
    public Horario findOne(Long id) {
        // TODO Auto-generated method stub
        return horarioDao.findById(id).orElse(null);
    }
    
}
