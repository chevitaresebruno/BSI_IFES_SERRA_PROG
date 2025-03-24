package brunocodes.com.model.operacoes;

import brunocodes.com.model.erros.BaseError;

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
