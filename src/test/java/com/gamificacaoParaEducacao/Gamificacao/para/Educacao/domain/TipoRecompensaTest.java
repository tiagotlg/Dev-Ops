package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TipoRecompensaTest {

    @Test
    void valueOfRetornaEnumCorreto() {
        assertEquals(TipoRecompensa.CRIPTOMOEDA, TipoRecompensa.valueOf("CRIPTOMOEDA"));
    }

    @Test
    void valuesContemTodosOsTipos() {
        assertTrue(java.util.Arrays.asList(TipoRecompensa.values()).contains(TipoRecompensa.CURSO_EXTRA));
    }
}
