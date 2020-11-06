package com.br.sati.Controller;

import com.br.sati.Model.Equipamento;
import com.br.sati.Model.SolicitacaoEquipamento;
import com.br.sati.Model.Usuario;
import com.br.sati.Service.NivelAcessoServiceImpl;
import com.br.sati.Service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Date;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    private UsuarioServiceImpl usuarioServiceImpl;

    private NivelAcessoServiceImpl nivelAcessoServiceImpl;
    @Autowired
    public UsuarioController(UsuarioServiceImpl usuarioServiceImpl, NivelAcessoServiceImpl nivelAcessoServiceImpl) {
        this.usuarioServiceImpl = usuarioServiceImpl;
        this.nivelAcessoServiceImpl = nivelAcessoServiceImpl;
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(@ModelAttribute("usuario") Usuario usuario,ModelMap model ) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("perfil", usuarioServiceImpl.RecuperarPorId(login));
        return new ModelAndView("perfil/perfil");
    }

    @PostMapping("/editar-senha")
    public ModelAndView alterarSenha (@ModelAttribute("usuario") Usuario usuario, RedirectAttributes attr){

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario1=usuarioServiceImpl.RecuperarPorId(login);
        usuario.setLogin(usuario1.getLogin());
        usuario.setNivelAcessos(usuario1.getNivelAcessos());
        usuario.setNomeCompleto(usuario1.getNomeCompleto());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getPassword()));

        usuarioServiceImpl.salvarUsuario(usuario);
        attr.addFlashAttribute("mensagem", "Alterado a senha  com sucesso.");

        return new ModelAndView("redirect:/usuario/perfil");
    }


    @GetMapping("/lista-usuario")
    public ModelAndView listarUsuario(ModelMap model ,RedirectAttributes attr) throws SQLException {
        model.addAttribute("usuarios", usuarioServiceImpl.listaUsuarios());
        return new ModelAndView("usuario/listausuario", model);
    }

    @GetMapping("/{id}/remover-usuario")
    public String remover (@PathVariable("id") String id , RedirectAttributes attr) {
        String mensagem =  usuarioServiceImpl.ExcluirUsuario(id);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/usuario/lista-usuario";
    }


    @GetMapping("/cadastro-usuario")
    public String preSalvar (@ModelAttribute("usuario") Usuario usuario, ModelMap model) throws SQLException {
        model.addAttribute("nivelacesso",nivelAcessoServiceImpl.listaNIvelAcesso());
        return "/usuario/cadastrousuario";
    }

    @PostMapping("/salvar-usuario")
    public String salvar(@Valid @ModelAttribute("solicitacao") Usuario usuario, BindingResult result, RedirectAttributes attr) throws SQLException {
        if (result.hasErrors()) {
            return "solicitacao/cadastrosolicitacao";
        }

        String mensagem = usuarioServiceImpl.salvarUsuario(usuario);
        attr.addFlashAttribute("mensagem", mensagem);
        return "redirect:/usuario/lista-usuario";

    }

}
