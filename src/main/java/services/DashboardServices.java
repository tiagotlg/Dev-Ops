package services;



import java.util.Collections;
import java.util.List;
import java.util.Map;

import entidades.Aluno;
import entidades.TipoRecompensa;

public class DashboardServices {

    private final List<Aluno> alunos;

    public DashboardServices(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    // BDD 1
    public long contarAlunosComCriptomoeda() {
        return 0L; // propositalmente errado
    }

    // Aux: conta por tipo
    public long contarAlunosPorTipo(TipoRecompensa tipo) {
        return 0L; // propositalmente errado
    }

    // Aux: lista alunos por tipo
    public List<String> alunosQueEscolheram(TipoRecompensa tipo) {
        return Collections.emptyList(); // propositalmente errado
    }

    // Distribuição (volume por tipo)
    public Map<TipoRecompensa, Long> distribuicaoPorTipo() {
        return Collections.emptyMap(); // propositalmente errado
    }

    // BDD 2: ranking por curso (para um tipo)
    public Map<String, Long> rankingCursosPorTipo(TipoRecompensa tipo) {
        return Collections.emptyMap(); // propositalmente errado
    }

    // BDD 2: top N cursos para um tipo
    public List<String> topNCursosPorTipo(TipoRecompensa tipo, int n) {
        return Collections.emptyList(); // propositalmente errado
    }
}