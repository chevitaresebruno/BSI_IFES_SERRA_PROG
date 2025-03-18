package model.serializers.operations;

import model.errors.BaseError;
import model.errors.serializers.SerializerError;
import model.errors.serializers.TryDivisionByZero;
import model.operations.aritimeticas.Divisao;
import model.serializers.AbstractSerializer;
import model.serializers.OperationSerializerResponseDTO;
import model.serializers.OperationSerializerSenderDTO;


public class DivisionSerializer extends AbstractSerializer
{
    public DivisionSerializer()
    {
        super(new Divisao());
    }

    @Override
    public OperationSerializerResponseDTO call(OperationSerializerSenderDTO operandos) throws SerializerError
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

