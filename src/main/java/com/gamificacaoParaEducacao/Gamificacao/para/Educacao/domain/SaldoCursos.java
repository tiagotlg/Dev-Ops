package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SaldoCursos {

    private int cursosDisponiveis;
    private int cursosConcluidos;

    public SaldoCursos adicionarBonusPorConclusao(double mediaFinal) {
        int novosDisponiveis = cursosDisponiveis;
        int novosConcluidos = cursosConcluidos + 1;

        if (mediaFinal >= 7.0) {
            novosDisponiveis += 3;
        }

        return new SaldoCursos(novosDisponiveis, novosConcluidos);
    }

    public SaldoCursos adicionarBonusParticipacao() {
        return new SaldoCursos(cursosDisponiveis + 1, cursosConcluidos);
    }

    public boolean atingiuLimiteParaPremium() {
        return cursosConcluidos >= 12;
    }

    public SaldoCursos consumirCurso() {
        if (cursosDisponiveis <= 0) {
            throw new IllegalStateException("Sem cursos disponíveis.");
        }
        return new SaldoCursos(cursosDisponiveis - 1, cursosConcluidos);
    }
}
