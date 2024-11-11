package adiantando_codigo.demo.model.domain.users;

import adiantando_codigo.demo.model.domain.users.auth.Password;
import adiantando_codigo.demo.model.domain.users.composer.CPF;

public abstract class User
{
    private CPF cpf;
    private String nome;
    private Password password;
    
    User(CPF cpf, String nome, Password password)
    {
        this.cpf = cpf;
        this.nome = nome;
        this.password = password;
    }

    public String CPFCode()
    {
        return this.cpf.get_code();
    }

    public boolean compare_password(String other_password) throws Exception
    {
        return this.password.compare(other_password);
    }

    public CPF get_cpf()
    {
        return this.cpf.copy();
    }

    public void set_cpf(String new_cpf)
    {
        this.cpf.set_code(new_cpf);
    }

    public String get_nome()
    {
        return this.nome.substring(0);
    }

    public void set_nome(String new_nome)
    {
        this.nome = new_nome;
    }

    public void set_password(Password new_password)
    {
        this.password = new_password;
    }

    public Password get_password()
    {
        return this.password;
    }
}
