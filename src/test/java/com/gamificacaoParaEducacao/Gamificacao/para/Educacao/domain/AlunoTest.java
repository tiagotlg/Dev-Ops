package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void possuiCriptomoedaRetornaTrueQuandoHaRecompensaDoTipo() {
        Recompensa recompensa = new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado");
        Aluno aluno = new Aluno("João", List.of(recompensa));

        assertTrue(aluno.possuiCriptomoeda());
    }

    @Test
    void possuiCriptomoedaRetornaFalseQuandoNaoHaRecompensas() {
        Aluno alunoComListaVazia = new Aluno("Ana", List.of());
        Aluno alunoSemLista = new Aluno();
        alunoSemLista.setNome("Maria");

        assertFalse(alunoComListaVazia.possuiCriptomoeda());
        assertFalse(alunoSemLista.possuiCriptomoeda());
    }

    @Test
    void equalsEHashCodeConsideramApenasId() {
        Aluno aluno1 = new Aluno();
        aluno1.setId(1L);
        aluno1.setNome("João");

        Aluno aluno2 = new Aluno();
        aluno2.setId(1L);
        aluno2.setNome("Outro nome");

        Aluno aluno3 = new Aluno();
        aluno3.setId(2L);
        aluno3.setNome("João");

        assertEquals(aluno1, aluno2);
        assertNotEquals(aluno1, aluno3);
    }
}
