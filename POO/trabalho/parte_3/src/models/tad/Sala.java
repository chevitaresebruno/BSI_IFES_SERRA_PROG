package models.tad;

import models.BaseModel;


public class Sala extends BaseModel<String>
{
    private final String bloco;
    private final String sala;
    private final String andar;
    
    public Sala(String bloco, String sala, String andar)
    {
        this.bloco = bloco;
        this.sala = sala;
        this.andar = andar;
    }

    public String codigo()
    {
        return String.format("%s%s%s", this.bloco, this.sala, this.andar);
    }

    @Override
    public boolean unique(String parameter)
    {
        return false;
    }
}

