package model.operations;

import model.errors.operations.DivisionByZero;

public class Divisao implements IOperation
{
    @Override
    public double calcular(double operando1, double operando2) throws DivisionByZero
    {
        if(operando2 == 0)
            throw new DivisionByZero();

        return operando1 / operando2;
    }   
}

