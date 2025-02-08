package models.usuarios;


public class Aluno extends Usuario{
    private double saldo;

    public Aluno(String cpf, String nome, String senha)
    {
        super(cpf, nome, senha);
        this.saldo = 0;
    }

    public boolean inserirSaldo(double valor)
    {
        if(valor < 0)
            { return false; }
        
        this.saldo += valor;

        return true;
    }

    public boolean retirarSaldo(double valor)
    {
        if(valor < 0)
            { return false; }
        
        if(this.saldo - valor < 0)
            { return false; }
        
        this.saldo -= valor;

        return true;
    }

    public double getSaldo()
    {
        return this.saldo;
    }

    @Override
    public String toString()
    {
        return String.format("%s (Saldo: R$%.2f)", super.toString(), this.saldo);
    }
}

