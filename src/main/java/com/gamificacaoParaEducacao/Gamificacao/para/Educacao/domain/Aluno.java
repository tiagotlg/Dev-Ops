package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    @Embedded
    private SaldoCursos saldoCursos;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "aluno_id")
    private List<Recompensa> recompensas;

    public Aluno(String nome, List<Recompensa> recompensas) {
        this.nome = nome;
        this.recompensas = recompensas;
    }

    public boolean possuiCriptomoeda() {
        if (recompensas == null) {
            return false;
        }
        return recompensas.stream()
                .anyMatch(r -> TipoRecompensa.CRIPTOMOEDA.equals(r.getTipo()));
    }
}
