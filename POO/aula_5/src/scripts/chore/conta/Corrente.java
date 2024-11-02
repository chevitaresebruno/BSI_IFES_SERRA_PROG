package scripts.chore.conta;

import scripts.chore.adt.Data;
import scripts.chore.usuarios.Gerente;
import scripts.chore.usuarios.Pessoa;

public class Corrente extends Conta
{
    public double limite; 

    public Corrente(String numero, Pessoa titular, Data criacao, Gerente ger)
    {
        super(numero, titular, criacao, ger);
        this.limite = 200d;
    }
    
    public Corrente(String numero, Pessoa titular, Data criacao, Gerente ger, String senha) throws Exception
    {
        super(numero, titular, criacao, ger, senha);
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

    public void set_limite(double limite)
    {
        this.limite = limite;
    }
}
