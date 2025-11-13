package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Aluno;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository.AlunoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceTest {

    private List<Aluno> fixture() {
        // João: CRIPTOMOEDA (Java Avançado)
        // Ana:  CRIPTOMOEDA (Java Avançado) + MOEDA
        // Maria: CURSO_EXTRA (Spring Boot)
        Aluno joao = new Aluno("João", List.of(
                new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado")
        ));

        Aluno ana = new Aluno("Ana", List.of(
                new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado"),
                new Recompensa(TipoRecompensa.MOEDA, null)
        ));

        Aluno maria = new Aluno("Maria", List.of(
                new Recompensa(TipoRecompensa.CURSO_EXTRA, "Spring Boot")
        ));

        return List.of(joao, ana, maria);
    }

    private DashboardService criarServiceCom(List<Aluno> alunos) {
        AlunoRepository repo = mock(AlunoRepository.class);
        when(repo.findAll()).thenReturn(alunos);
        return new DashboardService(repo);
    }

    @Test
    @DisplayName("Conta alunos com recompensa em criptomoeda")
    void contarAlunosComCriptomoeda() {
        DashboardService service = criarServiceCom(fixture());

        assertEquals(2L, service.contarAlunosComCriptomoeda());
    }

    @Test
    @DisplayName("Gera ranking de cursos por tipo")
    void rankingCursosPorTipo() {
        DashboardService service = criarServiceCom(fixture());

        Map<String, Long> ranking = service.rankingCursosPorTipo(TipoRecompensa.CRIPTOMOEDA);

        assertEquals(1, ranking.size());
        assertEquals(2L, ranking.get("Java Avançado"));
    }

    @Test
    @DisplayName("Quando não há alunos, retorna contagem e ranking vazios")
    void semAlunosRetornaVazio() {
        AlunoRepository repo = mock(AlunoRepository.class);
        when(repo.findAll()).thenReturn(List.of());

        DashboardService service = new DashboardService(repo);

        assertEquals(0L, service.contarAlunosComCriptomoeda());
        assertTrue(service.rankingCursosPorTipo(TipoRecompensa.CRIPTOMOEDA).isEmpty());
    }

    @Test
    @DisplayName("Quando tipo é null, ranking deve ser vazio")
    void rankingCursosPorTipoComTipoNullDeveRetornarVazio() {
        DashboardService service = criarServiceCom(fixture());

        assertTrue(service.rankingCursosPorTipo(null).isEmpty());
    }
}
