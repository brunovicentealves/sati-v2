package com.br.sati.Service;

import com.br.sati.Model.Usuario;

import java.util.List;

public interface UsuarioService {

    String salvarUsuario (Usuario usuario);
    List<Usuario> listaUsuarios();
    Usuario RecuperarPorId(String login);
    String atualizarUsuario(Usuario usuario);
    String ExcluirUsuario(String login);

}
