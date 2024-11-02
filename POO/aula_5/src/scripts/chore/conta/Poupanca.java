package scripts.chore.conta;

import scripts.chore.adt.Data;
import scripts.chore.usuarios.Gerente;
import scripts.chore.usuarios.Pessoa;

public class Poupanca extends Conta
{
    public Poupanca(String numero, Pessoa titular, Data criacao, Gerente ger)
    {
        super(numero, titular, criacao, ger);
    }
    
    public Poupanca(String numero, Pessoa titular, Data criacao, Gerente ger, String senha) throws Exception
    {
        super(numero, titular, criacao, ger, senha);
    }

    public void rendimentos(double juro)
    {
        if (juro > 0)
            this.saldo  *= ((juro+100)/100 + 1);
    }
}