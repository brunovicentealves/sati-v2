package com.br.sati.Repository;

import com.br.sati.Model.NivelAcesso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelAcessoRepository extends CrudRepository<NivelAcesso,String> {

}
