package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class AlunoTest {

    @Test
    void deveExporNomeERecompensas() {
        var recompensa = new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado");
        var aluno = new Aluno("João", List.of(recompensa));

        assertEquals("João", aluno.getNome());
        assertEquals(1, aluno.getRecompensas().size());
        assertSame(recompensa, aluno.getRecompensas().get(0));
    }
}
