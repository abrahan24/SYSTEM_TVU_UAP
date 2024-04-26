package com.example.tvu_sistema.Models.IService;
import java.util.List;

import com.example.tvu_sistema.Models.Entity.Persona;

public interface IPersonaService {
    public List<Persona> findAll();

	public void save(Persona persona);

	public Persona findOne(Long id_persona);

	public void delete(Long id);

	public Long obt_id_per_X_ci(String ci);

	public Long insertar_personaUsuario(
        String ap2,
        String ap1,
        String cip,
        Integer edadp,
        String nombrep,
        String num_celularp,
        Integer id_generop,
        String usuario_nomp,
        String correop,
        String contrasenap
        );

    public Long insertar_persona(
        String nombre,
        String ap1, 
        String ap2, 
        Integer edad, 
        String ci, 
        String num_celular,
        Integer id_genero);

    public List<Persona> lista_personas();

    public Persona getPersonaCI(String ci);

}
