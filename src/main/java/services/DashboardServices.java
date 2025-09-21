package services;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entidades.Aluno;
import entidades.Recompensa;
import entidades.TipoRecompensa;

public final class DashboardServices {

    private final List<Aluno> alunos;

    public DashboardServices(List<Aluno> alunos) {
        this.alunos = (alunos == null) ? List.of() : List.copyOf(alunos);
    }

    // BDD 1 — conta alunos que têm ao menos uma recompensa do tipo CRIPTOMOEDA
    public long contarAlunosComCriptomoeda() {
        return alunos.stream()
                .filter(a -> Optional.ofNullable(a.getRecompensas())
                        .orElseGet(List::of)
                        .stream()
                        .anyMatch(r -> r.getTipo() == TipoRecompensa.CRIPTOMOEDA))
                .count();
    }

    // BDD 2 — ranking de cursos por tipo de recompensa
    public Map<String, Long> rankingCursosPorTipo(TipoRecompensa tipo) {
        if (tipo == null) return Map.of();

        return streamRecompensas()
                .filter(r -> r.getTipo() == tipo)
                .collect(Collectors.groupingBy(Recompensa::getCurso, Collectors.counting()));
    }

    private Stream<Recompensa> streamRecompensas() {
        return alunos.stream()
                .map(Aluno::getRecompensas)
                .filter(Objects::nonNull)
                .flatMap(List::stream);
    }
}
