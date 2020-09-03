package com.br.sati.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProdutoController {

    @GetMapping("/cadastro-produto")
    public String home() {
        return "produto/cadastroproduto";
    }

}
