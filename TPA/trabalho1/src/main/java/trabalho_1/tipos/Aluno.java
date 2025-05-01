package trabalho_1.tipos;

public class Aluno implements Comparable<Aluno>
{
    protected String nome;
    protected String matricula;
    protected float nota;
    
    public Aluno(String nome, String matricula, float nota)
    {
        this.nome = nome;
        this.matricula = matricula;
        this.nota = nota;
    }

    public Aluno(String matricula)
    {
        this.nome = null;
        this.matricula = matricula;
        this.nota = 0;
    }

    @Override
    public int compareTo(Aluno outroAluno)
    {
        return this.matricula.compareTo(outroAluno.matricula);
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %.2f", this.nome, this.matricula, this.nota);
    }
}
