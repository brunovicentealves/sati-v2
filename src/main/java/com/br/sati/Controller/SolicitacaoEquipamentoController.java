package com.br.sati.Controller;

import com.br.sati.Model.Equipamento;
import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Service.EquipamentoServiceImple;
import com.br.sati.Service.FuncionarioServiceImpl;
import com.br.sati.Service.SolicitacaoEquipamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SolicitacaoEquipamentoController {
    @Autowired
    private SolicitacaoEquipamentoServiceImpl solicitacaoEquipamentoService ;

    @Autowired
    private EquipamentoServiceImple equipamentoServiceImpl ;
    @Autowired
    private FuncionarioServiceImpl  funcionarioServiceImpl;


    @GetMapping("solicitacao/lista-solicitacao")
    public ModelAndView listarsolicitacaoequipamento(ModelMap model) {
        model.addAttribute("solicitacoes",solicitacaoEquipamentoService.listaSolicitacaoEquipamentoStatus("Aguardando Aprovação"));
        return new ModelAndView("solicitacao/listasolicitacao", model);
    }

    @GetMapping("solicitacao/cadastro-solicitacao")
    public ModelAndView preSalvar (@ModelAttribute("solicitacao") SolicitacaoEquipamento solicitacaoEquipamento , ModelMap model){
        model.addAttribute("equipamento",equipamentoServiceImpl.lista());
        model.addAttribute("funcionario",funcionarioServiceImpl.listaFuncionario());
        return new ModelAndView("solicitacao/cadastrosolicitacao",model);
    }

    @PostMapping("/solicitacao/salvar-solicitacao")
    public String salvar(@Valid @ModelAttribute("solicitacao") SolicitacaoEquipamento solicitacaoEquipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "solicitacao/cadastrosolicitacao";
        }
        Date data = new Date();
        solicitacaoEquipamento.setStatus("Aguardando Aprovação");
        solicitacaoEquipamento.setData(data);
       solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);
        attr.addFlashAttribute("mensagem", "Solicitacao cadastrado  com sucesso.");
        return "redirect:/solicitacao/lista-solicitacao";

    }

    @GetMapping("solicitacao/{id}/remover-solicitacao")
    public String remover (@PathVariable("id") long id , RedirectAttributes attr){
        solicitacaoEquipamentoService.ExcluirSolicitacaoEquipamento(id);
        attr.addFlashAttribute("mensagem", "Solicitacao  Excluido  com sucesso.");
        return "redirect:/solicitacao/lista-solicitacao";
    }
    //********************************************
    //edpoint da tela de aprovação da solicitação
    //********************************************

    @GetMapping("solicitacao/lista-solicitacaoaprovacao")
    public ModelAndView listarSolicitacaoEquipamentoAprovacao(ModelMap model) {
        model.addAttribute("solicitacoes",solicitacaoEquipamentoService.listaSolicitacaoEquipamentoStatus("Aguardando Aprovação"));
        return new ModelAndView("solicitacao/listasolicitacaoaprovacao", model);
    }

    @GetMapping("solicitacao/{id}/editar-aprovar")
    public String aprovarSolicitacao (@PathVariable("id") long id ,RedirectAttributes attr){

       SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
       solicitacaoEquipamento.setStatus("Aprovado");
       solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);

        attr.addFlashAttribute("mensagem", "Aprovado com sucesso.");

        return "redirect:/solicitacao/lista-solicitacaoaprovacao";
    }

    @GetMapping("solicitacao/{id}/editar-reprovar")
    public String reprovarSolicitacao (@PathVariable("id") long id ,RedirectAttributes attr){

        SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
        solicitacaoEquipamento.setStatus("Reprovado");
        solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);

        attr.addFlashAttribute("mensagem", "Reprovado com sucesso.");

        return "redirect:/solicitacao/lista-solicitacaoaprovacao";
    }

}
