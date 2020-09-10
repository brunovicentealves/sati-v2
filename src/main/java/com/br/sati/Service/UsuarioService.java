package com.br.sati.Service;

import com.br.sati.Model.Usuario;

import java.util.List;

public interface UsuarioService {

    void salvarUsuario (Usuario usuario);
    List<Usuario> listaUsuarios();
    Usuario RecuperarPorId(String login);
    void atualizarUsuario(Usuario usuario);
    void ExcluirUsuario(String login);

}
