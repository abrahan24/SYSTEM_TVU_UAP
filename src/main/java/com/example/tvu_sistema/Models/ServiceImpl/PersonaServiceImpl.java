package com.example.tvu_sistema.Models.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IPersonaDao;
import com.example.tvu_sistema.Models.Entity.Persona;
import com.example.tvu_sistema.Models.IService.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService{

    @Autowired
    private IPersonaDao personaDao;

    @Override
    public List<Persona> findAll() {
        // TODO Auto-generated method stub
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    public void save(Persona persona) {
        // TODO Auto-generated method stub
        personaDao.save(persona);
    }

    @Override
    public Persona findOne(Long id) {
        // TODO Auto-generated method stub
        return personaDao.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        personaDao.deleteById(id);
    }

    @Override
    public Long obt_id_per_X_ci(String ci) {
        // TODO Auto-generated method stub
        return personaDao.obt_id_per_X_ci(ci);
    }

    @Override
    public Long insertar_personaUsuario(String ap2, String ap1, String cip, Integer edadp, String nombrep,
            String num_celularp, Integer id_generop, String usuario_nomp, String correop, String contrasenap) {
        // TODO Auto-generated method stub
        return personaDao.insertar_personaUsuario(ap2, ap1, cip, edadp, nombrep, num_celularp, id_generop, usuario_nomp, correop, contrasenap);
    }

    @Override
    public Long insertar_persona(String nombre, String ap1, String ap2, Integer edad, String ci, String num_celular,
            Integer id_genero) {
        // TODO Auto-generated method stub
        return personaDao.insertar_persona(nombre, ap1, ap2, edad, ci, num_celular, id_genero);
    }

    @Override
    public List<Persona> lista_personas() {
        // TODO Auto-generated method stub
        return (List<Persona>) personaDao.lista_personas();
    }

    @Override
    public Persona getPersonaCI(String ci) {
        return personaDao.getPersonaCI(ci);
    }    
}
