package brunocodes.com.model.operacoes.aritmeticas;

import brunocodes.com.model.erros.BaseError;
import brunocodes.com.model.operacoes.IOperacao;

public class Soma implements IOperacao
{
    private static Soma instance = null;

    private Soma()
    {

    }

    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return operando1 + operando2;
    }
    
    public static IOperacao getInstance()
    {
        if(Soma.instance == null)
        {
            Soma.instance = new Soma();
        }

        return Soma.instance;
    }
}
