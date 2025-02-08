package modules.adt;

public class Sala {
    private final String bloco;
    private final String sala;
    private final String andar;
    
    public Sala(String bloco, String sala, String andar)
    {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }

    public String nome()
    {
        return String.format("%s%s%s", this.bloco, this.sala, this.andar);
    }

    @Override
    public String toString() {
        return this.nome();
    }
}
