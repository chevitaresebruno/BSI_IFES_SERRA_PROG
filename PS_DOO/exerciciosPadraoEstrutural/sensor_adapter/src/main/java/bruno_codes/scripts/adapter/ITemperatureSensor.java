package bruno_codes.scripts.adapter;

import bruno_codes.scripts.adt.Celcius;


public interface ITemperatureSensor
{
    public Celcius ReadTemperature();
}

