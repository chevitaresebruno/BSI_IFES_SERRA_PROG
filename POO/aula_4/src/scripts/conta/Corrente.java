package scripts.conta;

import scripts.adt.Data;
import scripts.usuarios.Gerente;
import scripts.usuarios.Pessoa;

public class Corrente extends Conta
{
    public double limite; 

    public Corrente(String numero, Pessoa titular, Data criacao, Gerente ger)
    {
        super(numero, titular, criacao, ger);
        this.limite = 200d;
    }

    @Override
    public double disponivel()
    {
        return this.saldo + this.limite;
    }

    public void chequeEspecial(double juro)
    {
        if (juro > 0)
            if(this.saldo < 0)
                this.saldo  *= ((juro+100)/100);
    }
}
