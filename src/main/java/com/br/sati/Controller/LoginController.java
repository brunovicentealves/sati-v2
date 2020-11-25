package com.br.sati.Controller;



import com.br.sati.Service.SolicitacaoEquipamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


import java.sql.SQLException;

@Controller
public class LoginController {

    private SolicitacaoEquipamentoServiceImpl solicitacaoEquipamentoServiceImpl;

    @Autowired
    public LoginController(SolicitacaoEquipamentoServiceImpl solicitacaoEquipamentoServiceImpl) {
        this.solicitacaoEquipamentoServiceImpl = solicitacaoEquipamentoServiceImpl;
    }

    @GetMapping("/")
    public ModelAndView listarsolicitacaoequipamento(ModelMap model) throws SQLException {

        model.addAttribute("solicitacaoaprovada",solicitacaoEquipamentoServiceImpl.quantidadeSolicitacao("Aprovado"));
        model.addAttribute("solicitaAguardandoAprovacao",solicitacaoEquipamentoServiceImpl.quantidadeSolicitacao("Aguardando Aprovação"));
        model.addAttribute("solicitadoFornecedor",solicitacaoEquipamentoServiceImpl.quantidadeSolicitacao("Solicitado ao Fornecedor"));
        model.addAttribute("entregueequipamento",solicitacaoEquipamentoServiceImpl.quantidadeSolicitacaoaMesAberta());
        model.addAttribute("solicitacoes",solicitacaoEquipamentoServiceImpl.solicitacaoprioridade());
        return new ModelAndView("home", model);
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
