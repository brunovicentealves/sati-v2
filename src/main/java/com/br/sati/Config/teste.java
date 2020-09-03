package com.br.sati.Config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class teste {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("tbs@2020"));
    }
}
