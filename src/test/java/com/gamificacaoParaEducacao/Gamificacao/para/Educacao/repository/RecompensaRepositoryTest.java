package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.repository;

import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.Recompensa;
import com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain.TipoRecompensa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(properties = "spring.jpa.hibernate.ddl-auto=create-drop")
class RecompensaRepositoryTest {

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Test
    void deveSalvarERecuperarRecompensa() {
        Recompensa recompensa = new Recompensa(TipoRecompensa.MOEDA, "Curso X");

        Recompensa salvo = recompensaRepository.save(recompensa);

        Recompensa encontrado = recompensaRepository.findById(salvo.getId()).orElseThrow();

        assertThat(encontrado.getId()).isNotNull();
        assertThat(encontrado.getTipo()).isEqualTo(TipoRecompensa.MOEDA);
        assertThat(encontrado.getCurso()).isEqualTo("Curso X");
    }
}
