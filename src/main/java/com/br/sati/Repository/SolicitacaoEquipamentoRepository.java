package com.br.sati.Repository;

import com.br.sati.Model.SolicitacaoEquipamento;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SolicitacaoEquipamentoRepository extends CrudRepository<SolicitacaoEquipamento,Long> {

    List<SolicitacaoEquipamento> findByStatusOrderByDataInicioDesc(String status);

    @Query("Select count(s) from SolicitacaoEquipamento s where s.status=?1")
    public Integer quantidadeSolicitacaoAprovada(String status);

    @Query("SELECT s FROM SolicitacaoEquipamento s WHERE s.status=?1 OR s.status=?2 ORDER BY idSolicitacao")
    public List<SolicitacaoEquipamento> solicitacaoAprovadoSolicitadoForncedor(String status1,String status2);

    @Query("Select count(s) from SolicitacaoEquipamento s where  YEAR(s.dataEncerramento)= YEAR(?1) AND MONTH(s.dataEncerramento)= MONTH(?1) ")
    public Integer quantidadeSolicitacaoMesAberto (Date dataAtual);

    @Query("Select s from SolicitacaoEquipamento s where s.status='Aprovado' OR  s.status='Aguardando Aprovação' OR s.status='Solicitado ao Fornecedor' ORDER BY dataInicio")
    public List<SolicitacaoEquipamento> findSolicitacaoprioridade();


}
