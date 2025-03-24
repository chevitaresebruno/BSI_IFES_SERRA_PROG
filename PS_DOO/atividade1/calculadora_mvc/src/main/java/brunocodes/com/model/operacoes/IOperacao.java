package brunocodes.com.model.operacoes;

import brunocodes.com.model.erros.BaseError;

public interface IOperacao
{
    public double calcular(double operando1, double operando2) throws BaseError;    
}
