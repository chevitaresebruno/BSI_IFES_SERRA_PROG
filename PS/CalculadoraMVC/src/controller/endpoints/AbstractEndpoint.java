package controller.endpoints;

import controller.otp.OperatorResultDTO;
import controller.otp.OperatorSenderDTO;
import model.errors.serializers.SerializerError;
import model.serializers.AbstractSerializer;
import model.serializers.SerializerResponseDTO;
import model.serializers.SerializerSenderDTO;


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
            SerializerResponseDTO response = this.serializer.call(new SerializerSenderDTO(operacao.operando1, operacao.operando2));
            return new OperatorResultDTO(response.resultado);
        }
        catch(SerializerError e)
        {
            return new OperatorResultDTO(e.getMessage());
        }
    }
}

