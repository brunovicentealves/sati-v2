package com.br.sati.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ImplementsUserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/usuario/editar-senha").hasAnyRole("USER","ADMIN")
                .antMatchers("/perfil").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/lista-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/cadastro-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/salvar-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/{id}/atualizar-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/editar-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/{id}/visualizar-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/equipamento/{id}/remover-equipamento").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/lista-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/cadastro-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/salvar-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/{id}/atualizar-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/editar-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/{id}/visualizar-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/{id}/remover-solicitacao").hasAnyRole("USER","ADMIN")
                .antMatchers("/solicitacao/lista-solicitacaoaprovacao").hasAnyRole("ADMIN")
                .antMatchers("/solicitacao/{id}/editar-reprovar").hasAnyRole("ADMIN")
                .antMatchers("/solicitacao/{id}/editar-aprovar").hasAnyRole("ADMIN")
                .antMatchers("/historico/{id}/visualizar-historico").hasAnyRole("ADMIN")
                .antMatchers("/usuario/{id}/remover-usuario").hasAnyRole("ADMIN")
                .antMatchers("/usuario/lista-usuario").hasAnyRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).
                passwordEncoder(new BCryptPasswordEncoder());

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
