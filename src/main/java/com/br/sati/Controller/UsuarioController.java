package com.br.sati.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {


    @GetMapping("/perfil")
    public String perfil() {
        return "perfil/perfil";
    }

}
