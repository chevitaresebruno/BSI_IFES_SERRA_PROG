package model.errors.serializers;


public class TryDivisionByZero extends SerializerError
{

    public TryDivisionByZero()
    {
        super("Attention, you can`t divide a number by 0");
    }   
}

