package brunocodes.com.model.operacoes.aritmeticas;

import brunocodes.com.model.erros.BaseError;
import brunocodes.com.model.erros.DivisaoPorZeroErro;
import brunocodes.com.model.erros.DivisaoZeroPorZero;
import brunocodes.com.model.operacoes.IOperacao;

public class Divisao implements IOperacao
{
    private static Divisao instance = null;

    private Divisao()
    {

    }

    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        if(operando1 == 0 && operando2 == 0)
            { throw new DivisaoZeroPorZero(); }
        if(operando2 == 0)
            { throw new DivisaoPorZeroErro(); }
        
        return operando1 / operando2;
    }

    public static IOperacao getInstance()
    {
        if(Divisao.instance == null)
        {
            Divisao.instance = new Divisao();
        }

        return Divisao.instance;
    }
}
