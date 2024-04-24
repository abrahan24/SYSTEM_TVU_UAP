package com.example.tvu_sistema.Models.ServiceImpl;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IProgramaDao;
import com.example.tvu_sistema.Models.Entity.Programa;
import com.example.tvu_sistema.Models.IService.IProgramaService;

@Service
public class ProgramaServiceImpl implements IProgramaService{
    @Autowired
    private IProgramaDao programaDao;

    @Override
    public List<Programa> findAll() {
        // TODO Auto-generated method stub
        return (List<Programa>) programaDao.findAll();
    }

    @Override
    public void save(Programa programa) {
        // TODO Auto-generated method stub
        programaDao.save(programa);
    }

    @Override
    public Programa findOne(Long id) {
        // TODO Auto-generated method stub
        return programaDao.findById(id).orElse(null);
    }

    @Override
    public Long insertar_programa(String desc_programa, Time hr_empiezo_pogramaP, Time hr_fin_programaP,
            Integer id_horario, Integer id_persona) {
        // TODO Auto-generated method stub
        return programaDao.insertar_programa(desc_programa, hr_empiezo_pogramaP, hr_fin_programaP, id_horario, id_persona);
    }

    @Override
    public List<Programa> lista_programas() {
        // TODO Auto-generated method stub
        return (List<Programa>) programaDao.lista_programas();
    }

    

    

    

   

    
}
