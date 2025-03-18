package model.serializers.operations;

import model.operations.aritimeticas.Soma;
import model.serializers.AbstractSerializer;


public class SomaSerializer extends AbstractSerializer
{
    public SomaSerializer()
    {
        super(new Soma());
    }
}
