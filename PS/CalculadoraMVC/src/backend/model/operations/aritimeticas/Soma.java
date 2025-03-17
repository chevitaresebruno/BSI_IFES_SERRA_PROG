package model.operations.aritimeticas;

import model.errors.BaseError;
import model.operations.IOperation;

public class Soma implements IOperation
{
    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return operando1 + operando2;
    }
}
