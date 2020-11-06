package com.br.sati.Service;


import com.br.sati.Model.Equipamento;
import com.br.sati.Model.NivelAcesso;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NivelAcessoService {

    String salvarNivelAcesso(NivelAcesso nivelAcesso);
    List<NivelAcesso> listaNIvelAcesso() throws SQLException;
    Optional<NivelAcesso> RecuperarPorIdNivelAcesso(String nomeAcesso ) throws SQLException;
    String atualizarNivelAcesso(NivelAcesso nivelAcesso);
    String ExcluirNivelAcesso(String nomeAcesso) throws SQLException;

}
