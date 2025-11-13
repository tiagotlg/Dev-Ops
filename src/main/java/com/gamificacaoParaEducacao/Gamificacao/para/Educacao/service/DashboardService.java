package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.service;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Aluno;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final AlunoRepository alunoRepository;

    public DashboardService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public long contarAlunosComCriptomoeda() {
        List<Aluno> alunos = carregarAlunos();
        return alunos.stream()
                .filter(Aluno::possuiCriptomoeda)
                .count();
    }

    public Map<String, Long> rankingCursosPorTipo(TipoRecompensa tipo) {
        if (tipo == null) {
            return Map.of();
        }

        List<Aluno> alunos = carregarAlunos();
        if (alunos.isEmpty()) {
            return Map.of();
        }

        return streamRecompensas(alunos)
                .filter(r -> tipo.equals(r.getTipo()))
                .collect(Collectors.groupingBy(Recompensa::getCurso, Collectors.counting()));
    }

    private List<Aluno> carregarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos == null ? List.of() : List.copyOf(alunos);
    }

    private Stream<Recompensa> streamRecompensas(List<Aluno> alunos) {
        return alunos.stream()
                .map(Aluno::getRecompensas)
                .filter(Objects::nonNull)
                .flatMap(List::stream);
    }
}
