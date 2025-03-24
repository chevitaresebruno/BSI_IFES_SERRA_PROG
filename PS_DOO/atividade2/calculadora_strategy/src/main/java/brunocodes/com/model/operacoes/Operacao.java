package brunocodes.com.model.operacoes;

import brunocodes.com.model.erros.BaseError;
import brunocodes.com.model.erros.ComportamentoIndefinido;

public class Operacao implements Cloneable, IOperacao
{
    private IOperacao comportamento;

    public Operacao(IOperacao comportamento) throws ComportamentoIndefinido
    {
        if(comportamento == null)
        {
            throw new ComportamentoIndefinido();
        }
        
        this.comportamento = comportamento;
    }

    /*
     * Not safe!
     */
    public Operacao()
    {
        this.comportamento = null;
    }

    public void setComportamento(IOperacao comportamento) throws ComportamentoIndefinido
    {
        if(comportamento == null)
        {
            throw new ComportamentoIndefinido();
        }

        this.comportamento = comportamento;
    }

    @Override
    public Operacao clone() throws CloneNotSupportedException
    {
        return (Operacao)super.clone();
    }

    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return this.comportamento.calcular(operando1, operando2);
    }
}
