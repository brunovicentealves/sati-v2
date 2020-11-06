package com.br.sati.Controller;


import com.br.sati.Model.Equipamento;
import com.br.sati.Service.CategoriaEquipamentoServiceImpl;
import com.br.sati.Service.EquipamentoServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping("/equipamento")
public class EquipamentoController {

        private EquipamentoServiceImple equipamentoServiceImple;
        private CategoriaEquipamentoServiceImpl categoriaEquipamentoServiceImpl;

    @Autowired
    public EquipamentoController(EquipamentoServiceImple equipamentoServiceImple ,CategoriaEquipamentoServiceImpl categoriaEquipamentoServiceImpl) {
        this.equipamentoServiceImple = equipamentoServiceImple;
        this.categoriaEquipamentoServiceImpl=categoriaEquipamentoServiceImpl;
    }

    @GetMapping("/lista-equipamento")
        public ModelAndView listarProduto(ModelMap model ,RedirectAttributes attr) throws SQLException {
        model.addAttribute("equipamentos", equipamentoServiceImple.lista());
        return new ModelAndView("equipamento/listaequipamento", model);
    }

    @GetMapping("/cadastro-equipamento")
    public ModelAndView preSalvar (@ModelAttribute("equipamento") Equipamento equipamento, ModelMap model) throws SQLException {
        model.addAttribute("categorias",categoriaEquipamentoServiceImpl.listaCategoriaEquipamento());
        return new ModelAndView("equipamento/cadastroequipamento",model);
    }


    @PostMapping("/salvar-equipamento")
    public String salvar(@Valid @ModelAttribute("equipamento") Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "equipamento/cadastroequipamento";
        }
       String mensagem = equipamentoServiceImple.salvar(equipamento);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/equipamento/lista-equipamento";

    }

    @GetMapping("/{id}/atualizar-equipamento")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model) throws SQLException {
       Equipamento equipamento = equipamentoServiceImple.RecuperarPorId(id).get();
        model.addAttribute("equipamento", equipamento);
        return new ModelAndView("equipamento/editarequipamento",model);
    }

    @PostMapping("/editar-equipamento")
    public ModelAndView atualizar(@Valid @ModelAttribute("equipamento") Equipamento equipamento, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/equipamento/editarequipamento");
        }

        String mensagem = equipamentoServiceImple.atualizar(equipamento);
        attr.addFlashAttribute("mensagem", mensagem);
        return new ModelAndView("redirect:/equipamento/lista-equipamento");
    }

    @GetMapping("/{id}/visualizar-equipamento")
    public ModelAndView visualizarProduto(@PathVariable("id") long id, ModelMap model) throws SQLException {
        Equipamento equipamento = equipamentoServiceImple.RecuperarPorId(id).get();
        model.addAttribute("equipamento", equipamento);
        return new ModelAndView("equipamento/visualizarequipamento",model);
    }


    @GetMapping("/{id}/remover-equipamento")
    public String remover (@PathVariable("id") long id , RedirectAttributes attr) {
       String mensagem =  equipamentoServiceImple.Excluir(id);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/equipamento/lista-equipamento";
    }


}
