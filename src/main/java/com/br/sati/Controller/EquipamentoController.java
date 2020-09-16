package com.br.sati.Controller;


import com.br.sati.Model.Equipamento;
import com.br.sati.Service.EquipamentoServiceImple;
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
import java.util.Optional;

@Controller
public class EquipamentoController {

    @Autowired
    private EquipamentoServiceImple equipamentoServiceImple;

    @GetMapping("/equipamento/lista-equipamento")
    public ModelAndView listarProduto(ModelMap model) {
        model.addAttribute("equipamentos", equipamentoServiceImple.lista());
        return new ModelAndView("equipamento/listaequipamento", model);
    }

    @GetMapping("/equipamento/cadastro-equipamento")
    public String preSalvar (@ModelAttribute("equipamento") Equipamento equipamento){
       return "equipamento/cadastroequipamento";
    }


    @PostMapping("/equipamento/salvar-equipamento")
    public String salvar(@Valid @ModelAttribute("equipamento") Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "equipamento/cadastroequipamento";
        }
        equipamentoServiceImple.salvar(equipamento);
        attr.addFlashAttribute("mensagem", "Equipamento cadastrado  com sucesso.");
        return "redirect:/equipamento/lista-equipamento";

    }

    @GetMapping("equipamento/{id}/atualizar-equipamento")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) {
       Equipamento equipamento = equipamentoServiceImple.RecuperarPorId(id).get();
        model.addAttribute("equipamento", equipamento);
        return new ModelAndView("equipamento/editarequipamento",model);
    }

    @PostMapping("equipamento/editar-equipamento")
    public ModelAndView atualizar(@Valid @ModelAttribute("equipamento") Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/equipamento/editarequipamento");
        }

        equipamentoServiceImple.atualizar(equipamento);
        attr.addFlashAttribute("mensagem", "Equipamento atualizado com sucesso.");
        return new ModelAndView("redirect:/equipamento/lista-equipamento");
    }

    @GetMapping("equipamento/{id}/visualizar-equipamento")
    public ModelAndView visualizarProduto(@PathVariable("id") long id, ModelMap model) {
        Equipamento equipamento = equipamentoServiceImple.RecuperarPorId(id).get();
        model.addAttribute("equipamento", equipamento);
        return new ModelAndView("equipamento/visualizarequipamento",model);
    }



    @GetMapping("equipamento/{id}/remover-equipamento")
    public String remover (@PathVariable("id") long id , RedirectAttributes attr){
        equipamentoServiceImple.Excluir(id);
        attr.addFlashAttribute("mensagem", "Equipamento  Excluido  com sucesso.");
        return "redirect:/equipamento/lista-equipamento";
    }


}
