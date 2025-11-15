package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Aluno;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.SaldoCursos;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void deveSalvarERecuperarAlunoComRecompensasESaldoCursos() {
        SaldoCursos saldo = new SaldoCursos(3, 1);
        Recompensa recompensa = new Recompensa(TipoRecompensa.CURSO_EXTRA, "Spring Boot");

        Aluno aluno = new Aluno();
        aluno.setNome("João");
        aluno.setSaldoCursos(saldo);
        aluno.setRecompensas(List.of(recompensa));

        Aluno salvo = alunoRepository.save(aluno);

        Aluno encontrado = alunoRepository.findById(salvo.getId()).orElseThrow();

        assertThat(encontrado.getId()).isNotNull();
        assertThat(encontrado.getNome()).isEqualTo("João");
        assertThat(encontrado.getSaldoCursos()).isEqualTo(saldo);
        assertThat(encontrado.getRecompensas()).hasSize(1);
        assertThat(encontrado.getRecompensas().get(0).getCurso()).isEqualTo("Spring Boot");
    }
}
