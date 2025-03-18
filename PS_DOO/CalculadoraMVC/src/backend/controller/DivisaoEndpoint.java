package controller;

import model.serializers.operations.DivisionSerializer;


public class DivisaoEndpoint extends AbstractEndpoint
{
    public DivisaoEndpoint()
    {
        super(new DivisionSerializer());
    }
}

