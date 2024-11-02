package scripts.chore.conta;

import scripts.chore.adt.Data;
import scripts.chore.adt.Senha;
import scripts.chore.usuarios.Gerente;
import scripts.chore.usuarios.Pessoa;


public class Conta
{
    public String numero;
    public Pessoa titular;
    public Data criacao;
    public double saldo;
    public Gerente ger;
    public Senha senha;

    public Conta(String numero, Pessoa titular, Data criacao, Gerente ger)
    {
        this.numero = numero;
        this.titular = titular;
        this.criacao = criacao;
        this.ger = ger;

        this.saldo = 0.0d;

        this.senha = ger.senha;
        
        System.out.println("Nova conta criada no sistema.");
    }
    
    public Conta(String numero, Pessoa titular, Data criacao, Gerente ger, String senha) throws Exception
    {
        this.numero = numero;
        this.titular = titular;
        this.criacao = criacao;
        this.ger = ger;

        this.saldo = 0.0d;

        this.senha = new Senha(senha);
        
        System.out.println("Nova conta criada no sistema.");
    }

    public double disponivel()
    {
        return this.saldo;
    }
}
