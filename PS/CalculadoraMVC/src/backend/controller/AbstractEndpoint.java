package controller;

import controller.otp.OperatorResultDTO;
import controller.otp.OperatorSenderDTO;
import model.errors.serializers.SerializerError;
import model.serializers.AbstractSerializer;
import model.serializers.OperationSerializerResponseDTO;
import model.serializers.OperationSerializerSenderDTO;


public abstract class AbstractEndpoint
{
    private final AbstractSerializer serializer;

    public AbstractEndpoint(AbstractSerializer serializer)
    {
        this.serializer = serializer;
    }

    public OperatorResultDTO call(OperatorSenderDTO operacao)
    {
        try
        {
            OperationSerializerResponseDTO response = this.serializer.call(new OperationSerializerSenderDTO(operacao.operando1, operacao.operando2));
            return new OperatorResultDTO(response.resultado);
        }
        catch(SerializerError e)
        {
            return new OperatorResultDTO(e.getMessage());
        }
    }
}

