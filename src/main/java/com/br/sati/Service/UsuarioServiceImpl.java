package com.br.sati.Service;

import com.br.sati.Model.Usuario;
import com.br.sati.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl  implements UsuarioService{


     private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listaUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario RecuperarPorId(String login) {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void ExcluirUsuario(String login) {
        usuarioRepository.deleteById(login);
    }
}
