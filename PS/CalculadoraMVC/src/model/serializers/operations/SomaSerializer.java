package model.serializers.operations;

import model.operations.Soma;
import model.serializers.AbstractSerializer;


public class SomaSerializer extends AbstractSerializer
{
    public SomaSerializer()
    {
        super(new Soma());
    }
}
