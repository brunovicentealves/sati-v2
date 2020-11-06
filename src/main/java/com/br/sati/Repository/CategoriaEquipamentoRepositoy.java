package com.br.sati.Repository;

import com.br.sati.Model.CategoriaEquipamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaEquipamentoRepositoy  extends CrudRepository<CategoriaEquipamento,Long> {
}
