package com.br.sati;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;


@SpringBootApplication
public class SatiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SatiApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

    }

    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }
}
