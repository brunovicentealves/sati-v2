package com.br.sati;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SatiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatiApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("tbs@2020"));
	}

}
