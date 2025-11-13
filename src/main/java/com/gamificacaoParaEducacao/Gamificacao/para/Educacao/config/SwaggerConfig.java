package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Gamificação para Educação API",
                version = "v1",
                description = "API de exemplo construída com Clean Architecture e DDD."
        )
)
public class SwaggerConfig {
}
