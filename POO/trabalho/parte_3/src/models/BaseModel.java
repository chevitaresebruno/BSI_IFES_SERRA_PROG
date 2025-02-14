package models;


public abstract class BaseModel<CheckerParameter>
{
    public abstract boolean unique(CheckerParameter argg);
}

