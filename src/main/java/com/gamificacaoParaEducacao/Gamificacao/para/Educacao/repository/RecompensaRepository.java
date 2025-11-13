package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RecompensaRepository extends JpaRepository<Recompensa, Long> {
}
