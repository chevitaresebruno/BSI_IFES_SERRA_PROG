package adiantando_codigo.demo.model.domain.users;

import adiantando_codigo.demo.model.domain.users.auth.Password;
import adiantando_codigo.demo.model.domain.users.composer.CPF;
import adiantando_codigo.demo.model.domain.users.composer.Email;

public class Admin extends User
{
    private Email email;

    public Admin(CPF cpf, String nome, Password password, double balance, Email email)
    {
        super(cpf, nome, password);
        this.email = email;
    }

    public String emailText()
    {
        return this.email.get_text();
    }

    public Email get_email()
    {
        return this.email.copy();
    }

    public void set_email(Email email)
    {
        this.email = email;
    }
}
