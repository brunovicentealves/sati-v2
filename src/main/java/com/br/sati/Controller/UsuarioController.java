package com.br.sati.Controller;

import com.br.sati.Model.Usuario;
import com.br.sati.Service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class UsuarioController {

    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;

    }

    @GetMapping("/perfil")
    public ModelAndView perfil(@ModelAttribute("usuario") Usuario usuario,ModelMap model ) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("perfil", usuarioServiceImpl.RecuperarPorId(login));
        return new ModelAndView("perfil/perfil");
    }

    @PostMapping("/usuario/editar-senha")
    public ModelAndView alterarSenha (@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attr){

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario1=usuarioServiceImpl.RecuperarPorId(login);
        usuario.setLogin(usuario1.getLogin());
        usuario.setNivelAcessos(usuario1.getNivelAcessos());
        usuario.setNomeCompleto(usuario1.getNomeCompleto());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));

        usuarioServiceImpl.salvarUsuario(usuario);
        attr.addFlashAttribute("mensagem", "Alterado a senha  com sucesso.");

        return new ModelAndView("redirect:/perfil");
    }

}
