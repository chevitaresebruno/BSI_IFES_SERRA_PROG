package brunocodes.com.model.operacoes;

import brunocodes.com.model.erros.BaseError;
import brunocodes.com.model.erros.DivisaoPorZeroErro;
import brunocodes.com.model.erros.DivisaoZeroPorZero;

public class Divisao implements IOperacao
{
    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        if(operando1 == 0 && operando2 == 0)
            { throw new DivisaoZeroPorZero(); }
        if(operando2 == 0)
            { throw new DivisaoPorZeroErro(); }
        
        return operando1 / operando2;
    }
}
