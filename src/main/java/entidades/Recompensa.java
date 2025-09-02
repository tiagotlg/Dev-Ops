package entidades;

public class Recompensa {
    private final TipoRecompensa tipo;
    private final String curso; // nome do curso relacionado à recompensa (para ranking)

    public Recompensa(TipoRecompensa tipo, String curso) {
        this.tipo = tipo;
        this.curso = curso;
    }

    public TipoRecompensa getTipo() {
        return tipo;
    }

    public String getCurso() {
        return curso;
    }
}