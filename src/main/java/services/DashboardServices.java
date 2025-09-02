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

    // BDD 2
    public Map<String, Long> rankingCursosPorTipo(TipoRecompensa tipo) {
        return Collections.emptyMap(); // propositalmente errado
    }
}
