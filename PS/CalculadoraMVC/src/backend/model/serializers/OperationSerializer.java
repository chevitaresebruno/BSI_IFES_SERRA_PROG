package model.serializers;

import application.Constrains;
import model.operations.IOperation;


public final class OperationSerializer
{
    public static OperationSerializerResponseDTO chamar(OperationSerializerSenderDTO requisicao)
    {
        Class<?> c = Class.forName(String.format("%s.model.operations.%s", Constrains.BackendPackage, requisicao.operacao));

        if(IOperation.class.isAssignableFrom(c))
        {
            return new OperationSerializerResponseDTO(((IOperation)c.getDeclaredConstructor().newInstance()).calcular(requisicao.operando1, requisicao.operando2));
        }
        return new OperationSerializerResponseDTO(((IOperation)c.getDeclaredConstructor().newInstance()).calcular(requisicao.operando1, requisicao.operando2));
    }
}

