package com.br.sati;

import com.br.sati.Controller.SolicitacaoEquipamentoController;
import com.br.sati.Model.*;
import com.br.sati.Repository.HistoricoEquipamentoRepository;
import com.br.sati.Repository.NivelAcessoRepository;
import com.br.sati.Repository.SolicitacaoEquipamentoRepository;
import com.br.sati.Repository.UsuarioRepository;

import com.br.sati.Service.EquipamentoServiceImple;
import com.br.sati.Service.FuncionarioServiceImpl;
import com.br.sati.Service.SolicitacaoEquipamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;


@SpringBootApplication
public class SatiApplication implements CommandLineRunner {

    @Autowired
    private NivelAcessoRepository nivelAcessoRepository;
    @Autowired
     private UsuarioRepository usuarioRepository;

    @Autowired
    private FuncionarioServiceImpl funcionarioServiceImpl;

    @Autowired
    EquipamentoServiceImple equipamentoServiceImple;

    @Autowired
    private HistoricoEquipamentoRepository historicoEquipamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SatiApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("tbs@2020"));
	}

    @Override
    public void run(String... args) throws Exception {

        Usuario usuario = new Usuario();
        NivelAcesso nivelAcesso = new NivelAcesso();

        usuario.setLogin("teste2");
        usuario.setNomeCompleto("Teste1");
        usuario.setSenha("$2a$10$ORXFfXgWseHosoSOllXpqO8Zk4FJoUL/ywXK5LncxxkK0hZurKj6.");


        nivelAcesso.setNomeAcesso("ROLE_teste");

        usuario.getNivelAcessos().add(nivelAcesso);

        usuarioRepository.save(usuario);


        Funcionario funcionario = funcionarioServiceImpl.RecuperarPorIdFuncionario(Long.valueOf(1)).get();
        Equipamento equipamento = equipamentoServiceImple.RecuperarPorId(Long.valueOf(10)).get();

        HistoricoEquipamento historicoEquipamento = new HistoricoEquipamento();
        historicoEquipamento.setIdHistorico(Long.valueOf(1));
        historicoEquipamento.setEquipamento(equipamento);
        historicoEquipamento.setFuncionario(funcionario);
        Date data = new Date();
        historicoEquipamento.setData(data);
        historicoEquipamento.setPatrimonioMaquina("12123213");

        historicoEquipamentoRepository.save(historicoEquipamento);


    }
}
