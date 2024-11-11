package adiantando_codigo.demo.model.domain.users;

import adiantando_codigo.demo.model.domain.users.auth.Password;
import adiantando_codigo.demo.model.domain.users.composer.CPF;

public class Student extends User
{
    private double balance;
    
    public Student(CPF cpf, String nome, Password password, double balance)
    {
        super(cpf, nome, password);
        this.balance = balance;
    }

    public double get_balance()
    {
        return this.balance;
    }

    public void set_balance(double new_balance)
    {
        this.balance = new_balance;
    }
}
