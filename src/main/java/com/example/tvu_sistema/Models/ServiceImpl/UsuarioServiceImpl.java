package com.example.tvu_sistema.Models.ServiceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tvu_sistema.Models.Dao.IUsuarioDao;
import com.example.tvu_sistema.Models.Entity.Usuario;
import com.example.tvu_sistema.Models.IService.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public List<Usuario> findAll() {
        // TODO Auto-generated method stub
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    public void save(Usuario usuario) {
        // TODO Auto-generated method stub
        usuarioDao.save(usuario);
    }

    @Override
    public Usuario findOne(Long id) {
        // TODO Auto-generated method stub
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    public Integer validar_usuario(String usuario, String contrasena) {
        // TODO Auto-generated method stub
        return usuarioDao.validar_usuario(usuario, contrasena);
    }

    @Override
    public Long validar_usuario2(String usuario, String contrasena) {
        // TODO Auto-generated method stub
        return usuarioDao.validar_usuario2(usuario, contrasena);
    }

    
}
