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
    public String salvarUsuario(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return "Cadastrado com sucesso o usuário!";
        }catch (Exception e ){
            return "Não foi Possivel Cadastrar Usuario! --"+e.getMessage();
        }
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
    public String atualizarUsuario(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
            return "Atualzado com sucesso o Usuário!";
        }catch (Exception e ){
            return "Não foi possivel atualizar Usuário! --"+e.getMessage();
        }
    }

    @Override
    public String ExcluirUsuario(String login) {
        try {
            usuarioRepository.deleteById(login);
            return "Excluido com Sucesso Usuario!";
        }catch (Exception e ){
            return "Não possivel excluir porque o dados podem estar sendo usado em outra parte do sistema!";
        }

    }
}
