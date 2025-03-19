package brunocodes.com.model.operacoes;

import brunocodes.com.model.erros.BaseError;

public class Multiplicacao implements IOperacao
{
    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return operando1 * operando2;
    }
}
