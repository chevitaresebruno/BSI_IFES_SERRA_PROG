package modules.adt;

import modules.Admin;
import modules.Aluno;

public class UsuarioDTO
{
    private final String cpf, nome, senha;

    public static int ALUNO = 0;
    public static int ADMIN = 1;

    public UsuarioDTO(String cpf, String nome, String senha)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }   
    
    public Aluno toAluno()
    {
        return new Aluno(this.cpf, this.nome, this.senha);
    }

    public Admin toAdm(String email)
    {
        return new Admin(this.cpf, this.nome, this.senha, email);
    }
}
