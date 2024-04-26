package com.example.tvu_sistema.Models.Dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tvu_sistema.Models.Entity.Persona;

public interface  IPersonaDao extends CrudRepository<Persona, Long>{
    //----------------------------------------------------------------
    // Metodo para guardar los datos de una persona en la base de datos
    //----------------------------------------------------------------
    @Query(value = "select * from insertar_persona(?1, ?2, ?3, ?4, ?5, ?6, ?7) as mensaje", nativeQuery = true)
    public Long insertar_persona(
        String nombre,
        String ap1, 
        String ap2, 
        Integer edad, 
        String ci, 
        String num_celular,
        Integer id_genero);
    //----------------------------------------------------------------



    //----------------------------------------------------------------
    // Metodo para obtener el indentificador de la PERSONA por el CI en la base de datos
    //----------------------------------------------------------------
    @Query(value = "select * from obt_id_per_X_ci(?1)", nativeQuery = true)
    public Long obt_id_per_X_ci(String ci);
    //----------------------------------------------------------------



    //----------------------------------------------------------------
    // Metodo para guardar el los datos de una persona junto con los datos de su usuario en la base de datos 
    //----------------------------------------------------------------
    @Query(value = "select * from insertar_personaUsuario(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
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
    //----------------------------------------------------------------



    //----------------------------------------------------------------
    // Metodo para obtener la lista de persona activas en la base de datos
    //----------------------------------------------------------------
    @Query(value = "select * from lista_personas()", nativeQuery = true)
    public List<Persona> lista_personas();
    //----------------------------------------------------------------
    
    @Query("Select p from Persona p where p.ci = ?1 and p.estado_persona != 'X'")
    public Persona getPersonaCI(String ci);


}
