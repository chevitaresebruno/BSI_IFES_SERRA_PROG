package brunocodes.com.model.operacoes;

import brunocodes.com.model.erros.BaseError;

public class Subtracao implements IOperacao
{
    private static IOperacao instance = null;

    private Subtracao()
    {

    }

    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return operando1 - operando2;
    }

    public static IOperacao getInstance()
    {
        if(Subtracao.instance == null)
        {
            Subtracao.instance = new Subtracao();
        }

        return Subtracao.instance;
    }
}
