package com.br.sati.Repository;

import com.br.sati.Model.NivelAcesso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NivelAcessoRepository extends CrudRepository<NivelAcesso,String> {

    Optional<NivelAcesso> findBynomeAcesso(String nomeAcesso);
    NivelAcesso deleteBynomeAcesso(String nomeAcesso);
}
