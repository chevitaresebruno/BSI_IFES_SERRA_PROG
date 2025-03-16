package controller.endpoints;

import model.serializers.operations.SomaSerializer;


public class SomaEndpoint extends AbstractEndpoint
{
    public SomaEndpoint()
    {
        super(new SomaSerializer());
    }
}

