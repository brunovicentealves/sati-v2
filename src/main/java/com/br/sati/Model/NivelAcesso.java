package com.br.sati.Model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NivelAcesso implements GrantedAuthority {

    @Id
    private String nomeAcesso;

    @ManyToMany(cascade= CascadeType.PERSIST)
    private List<Usuario>usuarios = new ArrayList<>();

    public String getNomeAcesso() {
        return nomeAcesso;
    }

    public void setNomeAcesso(String nomeAcesso) {
        this.nomeAcesso = nomeAcesso;
    }

    @Override
    public String getAuthority() {

        return this.nomeAcesso;
    }
}
