package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaldoCursosTest {

    @Test
    void adicionarBonusPorConclusaoComMediaMaiorOuIgualSete() {
        SaldoCursos saldo = new SaldoCursos(1, 0);

        SaldoCursos resultado = saldo.adicionarBonusPorConclusao(7.5);

        assertEquals(4, resultado.getCursosDisponiveis());
        assertEquals(1, resultado.getCursosConcluidos());
    }

    @Test
    void adicionarBonusPorConclusaoComMediaMenorQueSete() {
        SaldoCursos saldo = new SaldoCursos(2, 1);

        SaldoCursos resultado = saldo.adicionarBonusPorConclusao(6.9);

        assertEquals(2, resultado.getCursosDisponiveis());
        assertEquals(2, resultado.getCursosConcluidos());
    }

    @Test
    void adicionarBonusParticipacaoIncrementaDisponiveis() {
        SaldoCursos saldo = new SaldoCursos(1, 5);

        SaldoCursos resultado = saldo.adicionarBonusParticipacao();

        assertEquals(2, resultado.getCursosDisponiveis());
        assertEquals(5, resultado.getCursosConcluidos());
    }

    @Test
    void atingiuLimiteParaPremiumQuandoConcluiuDozeOuMaisCursos() {
        assertTrue(new SaldoCursos(0, 12).atingiuLimiteParaPremium());
        assertTrue(new SaldoCursos(0, 20).atingiuLimiteParaPremium());
        assertFalse(new SaldoCursos(0, 11).atingiuLimiteParaPremium());
    }

    @Test
    void consumirCursoReduzQuantidadeDisponivel() {
        SaldoCursos saldo = new SaldoCursos(2, 3);

        SaldoCursos resultado = saldo.consumirCurso();

        assertEquals(1, resultado.getCursosDisponiveis());
        assertEquals(3, resultado.getCursosConcluidos());
    }

    @Test
    void consumirCursoSemCursosDisponiveisLancaExcecao() {
        SaldoCursos saldo = new SaldoCursos(0, 3);

        assertThrows(IllegalStateException.class, saldo::consumirCurso);
    }
}
