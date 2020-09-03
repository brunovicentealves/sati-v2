package com.br.sati.Repository;

import com.br.sati.Model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario,String> {

    Usuario findByLogin(String Login);
}
