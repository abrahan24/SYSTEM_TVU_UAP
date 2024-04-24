package com.example.tvu_sistema.Models.IService;

import java.util.List;

import com.example.tvu_sistema.Models.Entity.Usuario;

public interface IUsuarioService {
    public List<Usuario> findAll();

	public void save(Usuario usuario);

	public Usuario findOne(Long id);

	//public Integer validar_usuario(String usuario, String contrasena);

	//public Long validar_usuario2(String usuario, String contrasena);

	public Usuario getUsuarioContraseña(String nom_usuario, String contrasena);
}
