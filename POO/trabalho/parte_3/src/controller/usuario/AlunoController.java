package controller.usuario;

import models.usuarios.Aluno;


public final class AlunoController extends UsuarioController<Aluno>
{
    @Override
    public String toSaveDatabaseFormat(Aluno a)
    {
        return String.format("ALU\n%s", super.toSaveDatabaseFormat(a));
    }
}

