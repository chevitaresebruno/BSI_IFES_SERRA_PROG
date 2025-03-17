package controller;

import model.serializers.OperationSerializer;
import model.serializers.OperationSerializerResponseDTO;
import model.serializers.OperationSerializerSenderDTO;
import protocols.otp.OperatorResultDTO;
import protocols.otp.OperatorSenderDTO;

public class OperationEndpoint implements IOPTEndpoint
{
    public OperationEndpoint()
    {

    }

    @Override
    public OperatorResultDTO call(OperatorSenderDTO requisicao)
    {
        try
        {
            OperationSerializerResponseDTO response = OperationSerializer.chamar(new OperationSerializerSenderDTO(requisicao.operando1, requisicao.operando2, requisicao.operacao));
            return new OperatorResultDTO(response.resultado);
        }
        catch(Error e)
        {
            return new OperatorResultDTO(e.getMessage());
        }
    }
}
