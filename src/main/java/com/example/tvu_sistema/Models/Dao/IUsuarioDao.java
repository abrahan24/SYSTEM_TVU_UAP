package com.example.tvu_sistema.Models.Dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.tvu_sistema.Models.Entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
    /*
    @Query(value = "select * from validar_usuario(?1,?2)", nativeQuery = true)
    public Integer validar_usuario(String usuario, String contrasena);

    @Query(value = "select * from validar_usuario2(?1,?2)", nativeQuery = true)
    public Long validar_usuario2(String usuario, String contrasena);
    */


    @Query("select u from Usuario u where u.nom_usuario = ?1 and u.contrasena = ?2")
    public Usuario getUsuarioContraseña(String nom_usuario, String contrasena);
    
}
