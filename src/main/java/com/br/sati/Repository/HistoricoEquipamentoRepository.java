package com.br.sati.Repository;

import com.br.sati.Model.HistoricoEquipamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoricoEquipamentoRepository extends CrudRepository<HistoricoEquipamento,Long> {
}
