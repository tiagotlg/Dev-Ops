package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entidades.Aluno;
import entidades.Recompensa;
import entidades.TipoRecompensa;
import services.DashboardServices;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DashboardServiceTest {

    private List<Aluno> fixture() {
        // Cenário de dados fictício
        // João: CRIPTOMOEDA (Java Avançado)
        // Ana:  CRIPTOMOEDA (Java Avançado)
        // Maria: CURSO_EXTRA (Spring Boot)
        Aluno joao = new Aluno("João", List.of(
                new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado")
        ));
        Aluno ana = new Aluno("Ana", List.of(
                new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado")
        ));
        Aluno maria = new Aluno("Maria", List.of(
                new Recompensa(TipoRecompensa.CURSO_EXTRA, "Spring Boot")
        ));
        return List.of(joao, ana, maria);
    }

    @Test
    @DisplayName("BDD 1: Deve contar a quantidade de alunos que escolheram CRIPTOMOEDA")
    void deveContarAlunosComCriptomoeda() {
        var service = new DashboardServices(fixture());

        long resultado = service.contarAlunosComCriptomoeda();

        // Esperado: João e Ana => 2
        assertEquals(2L, resultado, "Deveria contar 2 alunos que escolheram CRIPTOMOEDA");
    }

    @Test
    @DisplayName("BDD 2: Deve exibir o ranking de cursos que mais geraram cada tipo de recompensa")
    void deveExibirRankingCursosPorTipo() {
        var service = new DashboardServices(fixture());

        Map<String, Long> ranking = service.rankingCursosPorTipo(TipoRecompensa.CRIPTOMOEDA);

        // Esperado: "Java Avançado" com 2 ocorrências
        assertEquals(2L, ranking.getOrDefault("Java Avançado", -1L),
                "Java Avançado deveria aparecer 2 vezes no ranking de CRIPTOMOEDA");
    }
}