package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long id;
    private String nome;
    private List<RecompensaDTO> recompensas;
}
