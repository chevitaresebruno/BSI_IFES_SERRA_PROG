package model.serializers;

import model.errors.serializers.SerializerError;
import model.operations.IOperation;


public abstract class AbstractSerializer
{
    protected IOperation operacao;

    public AbstractSerializer(IOperation operacao)
    {
        this.operacao = operacao;
    }

    public OperationSerializerResponseDTO call(OperationSerializerSenderDTO operandos) throws SerializerError
    {
        return new OperationSerializerResponseDTO(this.operacao.calcular(operandos.operando1, operandos.operando2));
    }
}

