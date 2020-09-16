package com.br.sati.Repository;

import com.br.sati.Model.SolicitacaoEquipamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoEquipamentoRepository extends CrudRepository<SolicitacaoEquipamento,Long> {

    List<SolicitacaoEquipamento> findByStatusOrderByDataDesc(String status);
}
