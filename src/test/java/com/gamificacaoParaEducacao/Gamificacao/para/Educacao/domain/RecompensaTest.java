package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecompensaTest {

    @Test
    void construtorEGettersFuncionam() {
        Recompensa recompensa = new Recompensa(TipoRecompensa.MOEDA, "Curso X");
        recompensa.setId(10L);

        assertEquals(10L, recompensa.getId());
        assertEquals(TipoRecompensa.MOEDA, recompensa.getTipo());
        assertEquals("Curso X", recompensa.getCurso());
    }
}
