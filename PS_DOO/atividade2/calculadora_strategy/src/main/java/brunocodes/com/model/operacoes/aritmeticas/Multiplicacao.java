package brunocodes.com.model.operacoes.aritmeticas;

import brunocodes.com.model.erros.BaseError;
import brunocodes.com.model.operacoes.IOperacao;

public class Multiplicacao implements IOperacao
{
    private static Multiplicacao instance = null;

    private Multiplicacao()
    {

    }

    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return operando1 * operando2;
    }

    public static IOperacao getInstance()
    {
        if(Multiplicacao.instance == null)
        {
            Multiplicacao.instance = new Multiplicacao();
        }
        
        return Multiplicacao.instance;
    }
}
