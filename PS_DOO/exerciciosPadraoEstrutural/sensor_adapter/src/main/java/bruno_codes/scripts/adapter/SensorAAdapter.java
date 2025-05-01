package bruno_codes.scripts.adapter;

import bruno_codes.lib.SensorA;
import bruno_codes.scripts.adt.Celcius;


public class SensorAAdapter implements ITemperatureSensor
{
    private final SensorA sensor;

    public SensorAAdapter(SensorA sensor)
    {
        this.sensor = sensor;
    }

    @Override
    public Celcius ReadTemperature()
    {
        return new Celcius(this.sensor.GetTemperatureInCelsius());
    }
}

