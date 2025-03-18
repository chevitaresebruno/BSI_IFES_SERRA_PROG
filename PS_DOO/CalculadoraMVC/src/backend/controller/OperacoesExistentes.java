package controller;

import model.serializers.ExistsOperationSerializer;
import protocols.titp.MultiTextInformationDTO;

public class OperacoesExistentes implements IMTIEndpoint
{
    public OperacoesExistentes()
    {

    }

    @Override
    public MultiTextInformationDTO call()
    {
        return new MultiTextInformationDTO(ExistsOperationSerializer.getOperations());
    }
    
}
