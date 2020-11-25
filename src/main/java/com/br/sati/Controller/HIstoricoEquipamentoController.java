package com.br.sati.Controller;

import com.br.sati.Model.Equipamento;
import com.br.sati.Model.HistoricoEquipamento;
import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Service.EquipamentoServiceImple;
import com.br.sati.Service.FuncionarioServiceImpl;
import com.br.sati.Service.HistoricoEquipamentoService;
import com.br.sati.Service.HistoricoEquipamentoServiceImpl;
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

@Controller
@RequestMapping("/historico")
public class HIstoricoEquipamentoController {

    private HistoricoEquipamentoServiceImpl historicoEquipamentoServiceimpl;
   private FuncionarioServiceImpl funcionarioServiceImpl;
   private EquipamentoServiceImple equipamentoServiceImpl;

   @Autowired
    public HIstoricoEquipamentoController(HistoricoEquipamentoServiceImpl historicoEquipamentoServiceimpl, FuncionarioServiceImpl funcionarioServiceImpl, EquipamentoServiceImple equipamentoServiceImpl) {
        this.historicoEquipamentoServiceimpl = historicoEquipamentoServiceimpl;
        this.funcionarioServiceImpl = funcionarioServiceImpl;
        this.equipamentoServiceImpl = equipamentoServiceImpl;
    }

    @GetMapping("/lista-historico")
    public ModelAndView listarsolicitacao(ModelMap model) throws SQLException {
        model.addAttribute("historico",historicoEquipamentoServiceimpl.listaHistorico());
        return new ModelAndView("/historicoequipamento/listahistorico", model);
    }

    @GetMapping("/cadastro-historico")
    public String preSalvarSolicitacao (@ModelAttribute("historico") HistoricoEquipamento historicoEquipamento , ModelMap model) throws SQLException {
        model.addAttribute("equipamento",equipamentoServiceImpl.lista());
        model.addAttribute("funcionario",funcionarioServiceImpl.listaFuncionario());
        return "historicoequipamento/cadastrohistorico";
    }

    @PostMapping("/salvar-historico")
    public String salvarSolicitacao(@Valid @ModelAttribute("historico") HistoricoEquipamento historicoEquipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "historicoequipamento/cadastrohistorico";
        }
        String mensagem = historicoEquipamentoServiceimpl.salvarHistorico(historicoEquipamento);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/historico/lista-historico";

    }

    @GetMapping("/{id}/atualizar-historico")
    public ModelAndView preAtualizarSolicitacao(@PathVariable("id") long id, ModelMap model) throws SQLException {
        HistoricoEquipamento historicoEquipamento = historicoEquipamentoServiceimpl.RecuperarPorIdHistorico(id).get();

        model.addAttribute("funcionario",  funcionarioServiceImpl.listaFuncionario());
        model.addAttribute("equipamento",  equipamentoServiceImpl.lista());
        model.addAttribute("historico", historicoEquipamento);
        return new ModelAndView("/historicoequipamento/editarhistorico",model);
    }


    @PostMapping("/editar-historico")
    public ModelAndView atualizar(@Valid @ModelAttribute("historico") HistoricoEquipamento historicoEquipamento , BindingResult result, RedirectAttributes attr) throws SQLException {
        if (result.hasErrors()) {
            return new ModelAndView("/historicoequipamento/editarhistorico");
        }
        HistoricoEquipamento historicoEquipamento1= historicoEquipamentoServiceimpl.RecuperarPorIdHistorico(historicoEquipamento.getIdHistorico()).get();
        historicoEquipamento.setDataAlteracao(historicoEquipamento1.getDataCriacao());
        historicoEquipamento.setLogUsuario(historicoEquipamento.getLogUsuario());
        String mensagem= historicoEquipamentoServiceimpl.atualizarHistorico(historicoEquipamento);
        attr.addFlashAttribute("mensagem", mensagem);
        return new ModelAndView("redirect:/historico/lista-historico");
    }


    @GetMapping("/{id}/remover-historico")
    public String removerSolicitacao (@PathVariable("id") long id , RedirectAttributes attr) {
       String mensagem = historicoEquipamentoServiceimpl.ExcluirHistorico(id);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/historico/lista-historico";

    }

    @GetMapping("/{id}/visualizar-historico")
    public ModelAndView visualizarSolicitacao(@PathVariable("id") long id, ModelMap model) throws SQLException {
        HistoricoEquipamento historicoEquipamento = historicoEquipamentoServiceimpl.RecuperarPorIdHistorico(id).get();
        model.addAttribute("historico", historicoEquipamento);
        return new ModelAndView("historicoequipamento/visualizarhistorico",model);
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
