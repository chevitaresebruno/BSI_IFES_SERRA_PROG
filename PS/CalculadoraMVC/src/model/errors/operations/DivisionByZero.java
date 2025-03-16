package model.errors.operations;

import model.errors.BaseError;


public class DivisionByZero extends BaseError
{

    public DivisionByZero()
    {
        super("Division by zero are not allowed.");
    }    
}

