package model.operations;

import model.errors.BaseError;

public class Soma implements IOperation
{
    @Override
    public double calcular(double operando1, double operando2) throws BaseError
    {
        return operando1 + operando2;
    }
}
