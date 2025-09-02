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
        // Dataset para os testes:
        // João: CRIPTOMOEDA (Java Avançado), CURSO_EXTRA (Git Essencial)
        // Ana:  CRIPTOMOEDA (Java Avançado)
        // Maria: VOUCHER (UX Labs), MOEDA (Data Science)
        Aluno joao = new Aluno("João", List.of(
                new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado"),
                new Recompensa(TipoRecompensa.CURSO_EXTRA, "Git Essencial")
        ));
        Aluno ana = new Aluno("Ana", List.of(
                new Recompensa(TipoRecompensa.CRIPTOMOEDA, "Java Avançado")
        ));
        Aluno maria = new Aluno("Maria", List.of(
                new Recompensa(TipoRecompensa.VOUCHER, "UX Labs"),
                new Recompensa(TipoRecompensa.MOEDA, "Data Science")
        ));
        return List.of(joao, ana, maria);
    }

    @Test
    @DisplayName("BDD 1: Ao filtrar por CRIPTOMOEDA deve contar alunos que escolheram essa recompensa")
    void deveContarAlunosComCriptomoeda() {
        var svc = new DashboardServices(fixture());
        long alunosComCripto = svc.contarAlunosComCriptomoeda();
        // Esperado: João e Ana => 2
        assertEquals(2L, alunosComCripto, "Deveria contar 2 alunos com CRIPTOMOEDA");
    }

    @Test
    @DisplayName("Contar por tipo (genérico) deve retornar a mesma quantidade do BDD 1 para CRIPTOMOEDA")
    void deveContarAlunosPorTipoCriptomoeda() {
        var svc = new DashboardServices(fixture());
        long qtde = svc.contarAlunosPorTipo(TipoRecompensa.CRIPTOMOEDA);
        assertEquals(2L, qtde, "CRIPTO deveria ter 2 alunos distintos");
    }

    @Test
    @DisplayName("Listar nomes dos alunos que escolheram VOUCHER")
    void deveListarAlunosQueEscolheramVoucher() {
        var svc = new DashboardServices(fixture());
        var nomes = svc.alunosQueEscolheram(TipoRecompensa.VOUCHER);
        assertEquals(1, nomes.size(), "Apenas Maria tem VOUCHER");
        assertTrue(nomes.contains("Maria"), "Deveria conter Maria");
    }

    @Test
    @DisplayName("Distribuição por tipo: CRIPTO=2, CURSO_EXTRA=1, VOUCHER=1, MOEDA=1")
    void deveCalcularDistribuicaoPorTipo() {
        var svc = new DashboardServices(fixture());
        Map<TipoRecompensa, Long> dist = svc.distribuicaoPorTipo();

        assertEquals(4, dist.size(), "Deveria ter 4 tipos presentes");
        assertEquals(2L, dist.getOrDefault(TipoRecompensa.CRIPTOMOEDA, -1L));
        assertEquals(1L, dist.getOrDefault(TipoRecompensa.CURSO_EXTRA, -1L));
        assertEquals(1L, dist.getOrDefault(TipoRecompensa.VOUCHER, -1L));
        assertEquals(1L, dist.getOrDefault(TipoRecompensa.MOEDA, -1L));
    }

    @Test
    @DisplayName("BDD 2: Ranking de cursos que mais geraram CRIPTOMOEDA (Java Avançado deve ser 2)")
    void deveExibirRankingCursosParaCriptomoeda() {
        var svc = new DashboardServices(fixture());
        Map<String, Long> ranking = svc.rankingCursosPorTipo(TipoRecompensa.CRIPTOMOEDA);

        assertFalse(ranking.isEmpty(), "Ranking não deveria estar vazio para CRIPTOMOEDA");
        assertEquals(2L, ranking.getOrDefault("Java Avançado", -1L),
                "Java Avançado deveria ter 2 ocorrências de CRIPTO");
    }

    @Test
    @DisplayName("Top 1 curso por CRIPTOMOEDA deve ser Java Avançado")
    void deveTrazerTopNCursosParaCriptomoeda() {
        var svc = new DashboardServices(fixture());
        var top1 = svc.topNCursosPorTipo(TipoRecompensa.CRIPTOMOEDA, 1);

        assertEquals(1, top1.size(), "Top 1 deveria retornar 1 curso");
        assertEquals("Java Avançado", top1.get(0), "Top 1 de CRIPTO deveria ser Java Avançado");
    }
}
