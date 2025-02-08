package database.filters.usuario;

import models.usuarios.Aluno;


public class AlunoFilter extends UsuarioFilter<Aluno>
{
    public AlunoFilter(String cpfProcurado)
    {
        super(cpfProcurado);
    }    
}

