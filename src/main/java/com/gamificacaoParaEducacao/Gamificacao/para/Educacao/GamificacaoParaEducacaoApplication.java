package com.gamificacaoParaEducacao.Gamificacao.para.Educacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = "com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain")
@EnableJpaRepositories(basePackages = "com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository")
public class GamificacaoParaEducacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamificacaoParaEducacaoApplication.class, args);
    }

}
