package model.serializers.operations;

import model.errors.BaseError;
import model.errors.serializers.SerializerError;
import model.errors.serializers.TryDivisionByZero;
import model.operations.Divisao;
import model.serializers.AbstractSerializer;
import model.serializers.SerializerResponseDTO;
import model.serializers.SerializerSenderDTO;


public class DivisionSerializer extends AbstractSerializer
{
    public DivisionSerializer()
    {
        super(new Divisao());
    }

    @Override
    public SerializerResponseDTO call(SerializerSenderDTO operandos) throws SerializerError
    {
        try
        {
            return super.call(operandos);
        }
        catch(BaseError e)
        {
            throw new TryDivisionByZero();
        }
    }
}

