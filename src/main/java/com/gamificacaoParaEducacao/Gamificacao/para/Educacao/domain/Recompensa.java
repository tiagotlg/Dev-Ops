package com.gamificacaoParaEducacao.Gamificacao.para.Educacao.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoRecompensa tipo;

    private String curso;

    public Recompensa(TipoRecompensa tipo, String curso) {
        this.tipo = tipo;
        this.curso = curso;
    }
}
