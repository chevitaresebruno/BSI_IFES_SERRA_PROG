package trabalho_1.tipos;

import java.util.Comparator;

public class ComparatorAluno implements Comparator<Aluno>
{

    private final boolean compararNome;
    private final boolean compararMatricula;
    private final boolean compararNota;

    public ComparatorAluno(boolean nome, boolean matricula, boolean nota)
    {
        this.compararNome = nome;
        this.compararMatricula = matricula;
        this.compararNota = nota;
    }

    public static ComparatorAluno comparadorPadrao()
    {
        return new ComparatorAluno(false, true, false);
    }

    @Override
    public int compare(Aluno al1, Aluno al2)
    {
        int valor = 0;

        if(this.compararMatricula)
        {
            valor = al1.matricula.compareTo(al2.matricula);
            return valor;
        }
        
        if(this.compararNome)
        {
            valor = al1.compareTo(al2);
            return valor;
        }

        if(this.compararNota)
        {
            if(al1.nota == al2.nota)
                { valor = 0; }
            else if(al1.nota > al2.nota)
                { valor = 1; }
            else
                { valor = -1; }
        }

        return valor;
    }
}
