package factory;

import models.usuarios.Admin;
import models.usuarios.Aluno;


public final class UsuarioFactory
{
    public static Aluno criar(String cpf, String nome, String senha)
    {
        return new Aluno(cpf, nome, senha);
    }   

    public static Admin criar(String cpf, String nome, String senha, String email)
    {
        return new Admin(cpf, nome, senha, email);
    }
}

