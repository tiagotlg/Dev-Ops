package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Aplicação funcionando!";
    }
}