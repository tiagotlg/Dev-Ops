package entidades;

import java.util.List;

public class Aluno {
    private final String nome;
    private final List<Recompensa> recompensas;

    public Aluno(String nome, List<Recompensa> recompensas) {
        this.nome = nome;
        this.recompensas = recompensas;
    }

    public String getNome() {
        return nome;
    }

    public List<Recompensa> getRecompensas() {
        return recompensas;
    }
}