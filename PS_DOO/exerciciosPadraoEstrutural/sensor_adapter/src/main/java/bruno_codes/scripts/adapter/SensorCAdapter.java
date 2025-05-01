package bruno_codes.scripts.adapter;

import bruno_codes.lib.SensorC;
import bruno_codes.scripts.adt.Celcius;


public class SensorCAdapter implements ITemperatureSensor
{
    private final SensorC sensor;

    public SensorCAdapter(SensorC sensor)
    {
        this.sensor = sensor;
    }    


    @Override
    public Celcius ReadTemperature()
    {
        return new Celcius(this.sensor.FetchTempC());
    }
}

