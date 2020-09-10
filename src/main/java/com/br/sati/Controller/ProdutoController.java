package com.br.sati.Controller;


import com.br.sati.Model.Produto;
import com.br.sati.Service.ProdutoServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoServiceImple produtoServiceImple;

    @GetMapping("/produto/listaproduto")
    public ModelAndView listarProduto(ModelMap model) {
        model.addAttribute("produtos", produtoServiceImple.listaProdutos());
        return new ModelAndView("produto/listaproduto", model);
    }

    @GetMapping("/produto/cadastroproduto")
    public String preSalvar (@ModelAttribute("produto") Produto produto){
       return "produto/cadastroproduto";
    }


    @PostMapping("/produto/salvarproduto")
    public String salvar(@Valid @ModelAttribute("produto") Produto produto , BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "produto/cadastroproduto";
        }
        produtoServiceImple.salvarProduto(produto);
        attr.addFlashAttribute("mensagem", "Equiamento cadastrado  com sucesso.");
        return "redirect:/produto/listaproduto";

    }


}
