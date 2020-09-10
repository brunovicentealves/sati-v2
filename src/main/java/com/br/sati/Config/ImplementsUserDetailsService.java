package com.br.sati.Config;

import com.br.sati.Model.Usuario;
import com.br.sati.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository ur;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = ur.findByLogin(login);

        if(usuario == null ){
            throw  new UsernameNotFoundException("Usuário não econtrado !");
        }
        return  new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
    }
}
