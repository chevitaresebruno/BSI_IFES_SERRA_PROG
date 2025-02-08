package database.services.usuario;

import database.filters.usuario.AlunoFilter;
import models.usuarios.Aluno;


public class AlunoService extends UsuarioService<Aluno, AlunoFilter>
{
    public AlunoService()
    {
        super();
    }
}

