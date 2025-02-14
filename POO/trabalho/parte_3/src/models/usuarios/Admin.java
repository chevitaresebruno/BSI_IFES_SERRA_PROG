package models.usuarios;


public class Admin extends Usuario
{
    private final String email;

    public Admin(String cpf, String nome, String senha, String email)
    {
        super(cpf, nome, senha);
        this.email = email;
    }

    public String getEmail()
    {
        return this.email;
    }

    @Override
    public boolean validarAcesso(String s)
    {
        // Esse método será sobrescrito numa próxima atividade.
        return super.validarAcesso(s);
    }

    @Override
    public String toString()
    {
        return String.format("%s EMAIL: %s, %s", super.toString(), this.email, "(ADMIN)");
    }
}

