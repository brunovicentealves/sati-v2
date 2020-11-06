package com.br.sati.Controller;


import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Service.EquipamentoServiceImple;
import com.br.sati.Service.FuncionarioServiceImpl;
import com.br.sati.Service.HistoricoEquipamentoServiceImpl;
import com.br.sati.Service.SolicitacaoEquipamentoServiceImpl;
import com.br.sati.Util.GerarPdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;


import static com.br.sati.Util.DataFormatada.FormatandoData;
import static com.br.sati.Util.DataFormatada.FormatandoPadrao;

@Controller
@RequestMapping("/solicitacao")
public class SolicitacaoEquipamentoController {

    private SolicitacaoEquipamentoServiceImpl solicitacaoEquipamentoService ;
    private EquipamentoServiceImple equipamentoServiceImpl ;
    private FuncionarioServiceImpl  funcionarioServiceImpl;
    private HistoricoEquipamentoServiceImpl historicoEquipamentoServiceImpl;

    @Autowired
    public SolicitacaoEquipamentoController(SolicitacaoEquipamentoServiceImpl solicitacaoEquipamentoService, EquipamentoServiceImple equipamentoServiceImpl, FuncionarioServiceImpl funcionarioServiceImpl, HistoricoEquipamentoServiceImpl historicoEquipamentoServiceImpl) {
        this.solicitacaoEquipamentoService = solicitacaoEquipamentoService;
        this.equipamentoServiceImpl = equipamentoServiceImpl;
        this.funcionarioServiceImpl = funcionarioServiceImpl;
        this.historicoEquipamentoServiceImpl = historicoEquipamentoServiceImpl;
    }


    @GetMapping("/lista-solicitacao")
    public ModelAndView listarSolicitacaoEquipamento(ModelMap model) throws SQLException {
        model.addAttribute("solicitacoes",solicitacaoEquipamentoService.listaSolicitacaoEquipamentoStatus("Aguardando Aprovação"));
        return new ModelAndView("/solicitacao/listasolicitacao", model);
    }

    @GetMapping("/lista-solicitacao-acompanhamento")
    public ModelAndView listarSolicitacaoEquipamentoAcompanhamento(ModelMap model) throws SQLException {
        model.addAttribute("solicitacoes",solicitacaoEquipamentoService.listaSolicitacaoAprovadaeSolicitadoFornecedor("Aprovado","Solicitado ao Fornecedor"));
        return new ModelAndView("/acompanhamento/listasolicitacaoacompanhamento", model);
    }

    @GetMapping("/cadastro-solicitacao")
    public ModelAndView preSalvarSolicitacao (@ModelAttribute("solicitacao") SolicitacaoEquipamento solicitacaoEquipamento , ModelMap model) throws SQLException {
        model.addAttribute("equipamento",equipamentoServiceImpl.lista());
        model.addAttribute("funcionario",funcionarioServiceImpl.listaFuncionario());
        return new ModelAndView("solicitacao/cadastrosolicitacao",model);
    }

    @PostMapping("/salvar-solicitacao")
    public String salvarSolicitacao(@Valid @ModelAttribute("solicitacao") SolicitacaoEquipamento solicitacaoEquipamento, BindingResult result, RedirectAttributes attr) throws SQLException, ParseException {
        if (result.hasErrors()) {
            return "/solicitacao/cadastrosolicitacao";
        }

        solicitacaoEquipamento.setStatus("Aguardando Aprovação");
        solicitacaoEquipamento.setData(FormatandoData());
      String mensagem = solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/solicitacao/lista-solicitacao";

    }

    @GetMapping("/{id}/atualizar-solicitacao")
    public ModelAndView preAtualizarSolicitacao(@PathVariable("id") long id, ModelMap model) throws SQLException {
       SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
        model.addAttribute("funcionario",  funcionarioServiceImpl.listaFuncionario());
        model.addAttribute("equipamento",  equipamentoServiceImpl.lista());
        model.addAttribute("solicitacao", solicitacaoEquipamento);
        return new ModelAndView("solicitacao/editasolicitacao",model);
    }


    @PostMapping("/editar-solicitacao")
    public ModelAndView atualizarSolicitacao(@Valid @ModelAttribute("solicitacao") SolicitacaoEquipamento solicitacaoEquipamento , BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/solicitacao/editasolicitacao");
        }
        SolicitacaoEquipamento solicitacaoEquipamento1 = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(solicitacaoEquipamento.getIdSolicitacao()).get();
        solicitacaoEquipamento.setStatus(solicitacaoEquipamento1.getStatus());
        solicitacaoEquipamento.setData(solicitacaoEquipamento1.getData());
        String mensagem= solicitacaoEquipamentoService.atualizarSolicitacaoEquipamento(solicitacaoEquipamento);
        attr.addFlashAttribute("mensagem", mensagem);
        return new ModelAndView("redirect:/solicitacao/lista-solicitacao");
    }

    @GetMapping("/{id}/visualizar-solicitacao")
    public ModelAndView visualizarSolicitacao(@PathVariable("id") long id, ModelMap model) throws ParseException {
        SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
      
        model.addAttribute("solicitacao", solicitacaoEquipamento);
        return new ModelAndView("solicitacao/visualizacaosolicitacao",model);
    }




    @GetMapping("/{id}/remover-solicitacao")
    public String removerSolictacao (@PathVariable("id") long id , RedirectAttributes attr){
       String mensagem= solicitacaoEquipamentoService.ExcluirSolicitacaoEquipamento(id);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/solicitacao/lista-solicitacao";
    }
    //********************************************
    //edpoint da tela de aprovação da solicitação
    //********************************************

    @GetMapping("/lista-solicitacaoaprovacao")
    public ModelAndView listarSolicitacaoEquipamentoAprovacao(ModelMap model) throws SQLException {
        model.addAttribute("solicitacoes",solicitacaoEquipamentoService.listaSolicitacaoEquipamentoStatus("Aguardando Aprovação"));
        return new ModelAndView("aprovacao/listasolicitacaoaprovacao", model);
    }

    @GetMapping("/{id}/editar-aprovar")
    public String statusAprovarSolicitacao (@PathVariable("id") long id ,RedirectAttributes attr) throws SQLException {

       SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
       solicitacaoEquipamento.setStatus("Aprovado");
       solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);

        attr.addFlashAttribute("mensagem", "Alterado Status com sucesso.");

        return "redirect:/solicitacao/lista-solicitacaoaprovacao";
    }

    @GetMapping("/{id}/editar-reprovar")
    public String statusReprovarSolicitacao (@PathVariable("id") long id ,RedirectAttributes attr) throws SQLException {

        SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
        solicitacaoEquipamento.setStatus("Reprovado");
        solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);

        attr.addFlashAttribute("mensagem", "Alterado com sucesso.");

        return "redirect:/solicitacao/lista-solicitacaoaprovacao";
    }

    @GetMapping("/{id}/solicitado-fornecedor")
    public String solicitarFornecedor (@PathVariable("id") long id ,RedirectAttributes attr) throws SQLException {

        SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
        solicitacaoEquipamento.setStatus("Solicitado ao Fornecedor");
        solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);

        attr.addFlashAttribute("mensagem", "Alterado Status com sucesso.");

        return "redirect:/solicitacao/lista-solicitacao-acompanhamento";
    }

    @GetMapping("/{id}/entregue-equipamento")
    public String entregueEquipamento (@PathVariable("id") long id ,RedirectAttributes attr) throws SQLException {

        SolicitacaoEquipamento solicitacaoEquipamento = solicitacaoEquipamentoService.RecuperarPorIdSolicitacaoEquipamento(id).get();
        solicitacaoEquipamento.setStatus("Entregue Equipamento");
        solicitacaoEquipamentoService.salvarSolicitacaoEquipamento(solicitacaoEquipamento);

        attr.addFlashAttribute("mensagem", "Alterado Status com sucesso.");

        return "redirect:/solicitacao/lista-solicitacao-acompanhamento";
    }

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> playlistReport(){
        ByteArrayInputStream bis = GerarPdfReport.gerarPdfContrato();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Contrato.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
