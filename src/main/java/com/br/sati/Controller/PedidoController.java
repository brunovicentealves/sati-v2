package com.br.sati.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.text.AttributedString;

@Controller
public class PedidoController {


    @GetMapping("/lista-pedidos-aprovacao")
    public String listasolicitacao() {
        return "listasolicitacao";
    }



}
