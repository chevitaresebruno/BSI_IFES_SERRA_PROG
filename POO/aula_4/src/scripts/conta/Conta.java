package scripts.conta;

import scripts.adt.Data;
import scripts.usuarios.Gerente;
import scripts.usuarios.Pessoa;

public class Conta {
    public String numero;
    public Pessoa titular;
    public Data criacao;
    public double saldo;
    public Gerente ger;

    public Conta(String numero, Pessoa titular, Data criacao, Gerente ger)
    {
        this.numero = numero;
        this.titular = titular;
        this.criacao = criacao;
        this.ger = ger;

        this.saldo = 0.0d;

        System.out.println("Nova conta criada no sistema.");
    }
    
    public double disponivel()
    {
        return this.saldo;
    }

    public void extrato()
    {
        System.out.println ("***EXTRATODACONTA***");
        System.out.println("Conta: " + this.numero);
        System.out.println("Titular: " + this.titular.cpf);
        System.out.println("Saldo disponivel para saque: " + this.disponivel());
    }

    public void depositar(double valor)
    {
        if(valor > 0)
            this.saldo += valor;
    }

    public boolean sacar(double valor)
    {
        if(valor < 0 || this.disponivel() < valor)
        {
            System.out.println("ERRO: Saque na conta " + this.numero + " nao foi realizado. Valor disponivel: " + this.disponivel());
            return false;
        }    
        
        this.saldo -= valor;

        System.out.println("Saque na conta " + this.numero + " realizado com sucesso. Novo saldo: " + this.saldo);

        return true;
    }

    public boolean transferir(double valor, Conta destino)
    {
        if(valor <= 0)
            return false;

        if(this.sacar(valor))
        {
            destino.depositar(valor);
            return true;
        }
        
        return  false;
    }
}
