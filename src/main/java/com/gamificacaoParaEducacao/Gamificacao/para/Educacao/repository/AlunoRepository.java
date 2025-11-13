package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
