package scripts.conta;

import scripts.adt.Data;
import scripts.usuarios.Gerente;
import scripts.usuarios.Pessoa;

public class Poupanca extends Conta
{
    public Poupanca(String numero, Pessoa titular, Data criacao, Gerente ger)
    {
        super(numero, titular, criacao, ger);
    }

    public void rendimentos(double juro)
    {
        if (juro > 0)
            this.saldo  *= ((juro+100)/100 + 1);
    }
}