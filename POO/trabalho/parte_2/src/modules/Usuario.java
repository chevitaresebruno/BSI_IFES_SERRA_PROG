package modules;
public class Usuario
{
    protected String cpf, nome;
    private String senha;

    public Usuario(String cpf, String nome, String senha)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public boolean validarAcesso(String s)
    {
        return s.equals(this.senha);
    }

    public boolean alterarSenha(String atual, String nova)
    {
        if(this.validarAcesso(atual))
            {
                this.senha = nova;
                return true;
            }
        
        return false;
    }

    public String getCPF()
    {
        return this.cpf;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s - CPF: %s", this.nome, this.cpf);
    }
}
