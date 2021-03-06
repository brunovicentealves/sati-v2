package com.br.sati.Model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String login;

    private String nomeCompleto;

    private String senha;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "usuario_acesso",
            joinColumns = @JoinColumn(
                    name = "usuario_id", referencedColumnName = "login"),
            inverseJoinColumns = @JoinColumn(
                    name = "acesso_id", referencedColumnName = "nomeAcesso"))
    private List<NivelAcesso> nivelAcessos = new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<NivelAcesso> getNivelAcessos() {
        return nivelAcessos;
    }

    public void setNivelAcessos(List<NivelAcesso> nivelAcessos) {
        this.nivelAcessos = nivelAcessos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return nivelAcessos;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
