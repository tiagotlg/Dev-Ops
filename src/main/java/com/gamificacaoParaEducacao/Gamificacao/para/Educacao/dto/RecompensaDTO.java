package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecompensaDTO {

    private Long id;
    private String tipo;
    private String curso;
}
