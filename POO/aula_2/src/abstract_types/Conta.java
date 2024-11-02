package abstract_types;

public class Conta {
    public String numero;
    public Pessoa titular;
    public double saldo;
    public double limite; 

    public double disponivel()
    {
        return this.saldo + this.limite;
    }

    public void extrato()
    {
        System.out.println ("***EXTRATODACONTA***");
        System.out.println("Conta: " + this.numero);
        System.out.println("Titular: " + this.titular.cpf);
        System.out.println("Saldo disponivel para saque: "+this.disponivel());
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


    public void chequeEspecial(double juro)
    {
        if (juro > 0)
            if(this.saldo < 0)
                this.saldo  *= ((juro+100)/100);
    }
}
