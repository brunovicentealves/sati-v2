package com.br.sati;

import com.br.sati.Model.NivelAcesso;
import com.br.sati.Model.Usuario;
import com.br.sati.Repository.NivelAcessoRepository;
import com.br.sati.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class SatiApplication implements CommandLineRunner {

    @Autowired
    private NivelAcessoRepository nivelAcessoRepository;
    @Autowired
     private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(SatiApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("tbs@2020"));



	}

    @Override
    public void run(String... args) throws Exception {

        Usuario usuario = new Usuario();
        NivelAcesso nivelAcesso = new NivelAcesso();

        usuario.setLogin("teste2");
        usuario.setNomeCompleto("Teste1");
        usuario.setSenha("$2a$10$ORXFfXgWseHosoSOllXpqO8Zk4FJoUL/ywXK5LncxxkK0hZurKj6.");


        nivelAcesso.setNomeAcesso("ROLE_teste");

        usuario.getNivelAcessos().add(nivelAcesso);

        usuarioRepository.save(usuario);


    }
}
