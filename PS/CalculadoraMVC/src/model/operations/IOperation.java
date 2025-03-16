package model.operations;

import model.errors.BaseError;


public interface IOperation
{
    public double calcular(double operando1, double operando2) throws BaseError;
}

