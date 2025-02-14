package models.usuarios;

import models.BaseModel;
import models.tad.Senha;


public abstract class Usuario extends BaseModel<String> implements Comparable<Usuario>
{
    protected String cpf, nome;
    private Senha senha;

    public Usuario(String cpf, String nome, String senha)
    {
        this(cpf, nome, new Senha(senha));
    }

    public Usuario(String cpf, String nome, Senha senha)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean validarAcesso(String s)
    {
        return this.senha.compareTo(s) == 0;
    }

    public boolean alterarSenha(String atual, String nova)
    {
        if(this.validarAcesso(atual))
            {
                this.senha = new Senha(nova);
                return true;
            }
        
        return false;
    }

    public String getCPF()
    {
        return this.cpf;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getSenhaToDatabase()
    {
        return this.senha.saveInDatabase();
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - %s", this.nome, this.cpf);
    }

    @Override
    public boolean unique(String parameter)
    {
        return this.cpf.equals(cpf);
    }

    @Override
    public int compareTo(Usuario u)
    {
        return this.cpf.compareTo(u.cpf);
    }
}

