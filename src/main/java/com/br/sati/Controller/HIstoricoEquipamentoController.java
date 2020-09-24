package com.br.sati.Controller;

import com.br.sati.Model.HistoricoEquipamento;
import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Service.HistoricoEquipamentoService;
import com.br.sati.Service.HistoricoEquipamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
public class HIstoricoEquipamentoController {

    private HistoricoEquipamentoServiceImpl historicoEquipamentoServiceimpl;
    @Autowired

    public HIstoricoEquipamentoController(HistoricoEquipamentoServiceImpl historicoEquipamentoServiceimpl) {
        this.historicoEquipamentoServiceimpl = historicoEquipamentoServiceimpl;
    }

    @GetMapping("/historico/lista-historico")
    public ModelAndView listarsolicitacaoequipamento(ModelMap model) throws SQLException {
        model.addAttribute("historico",historicoEquipamentoServiceimpl.listaHistorico());
        return new ModelAndView("/historicoequipamento/listahistorico", model);
    }


    @GetMapping("/historico/{id}/remover-historico")
    public String remover (@PathVariable("id") long id , RedirectAttributes attr) {
       String mensagem = historicoEquipamentoServiceimpl.ExcluirHistorico(id);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/historico/lista-historico";

    }

    @GetMapping("/historico/{id}/visualizar-historico")
    public ModelAndView visualizarSolicitacao(@PathVariable("id") long id, ModelMap model) throws SQLException {
        HistoricoEquipamento historicoEquipamento = historicoEquipamentoServiceimpl.RecuperarPorIdHistorico(id).get();
        model.addAttribute("historico", historicoEquipamento);
        return new ModelAndView("historicoequipamento/visualizarhistorico",model);
    }
}
