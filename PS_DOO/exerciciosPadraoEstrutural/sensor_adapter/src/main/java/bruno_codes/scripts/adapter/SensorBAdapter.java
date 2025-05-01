package bruno_codes.scripts.adapter;
import bruno_codes.lib.SensorB;
import bruno_codes.scripts.adt.Celcius;


public class SensorBAdapter implements ITemperatureSensor
{
    private final SensorB sensor;
    
    public SensorBAdapter(SensorB sensor)
    {
        this.sensor = sensor;
    }


    @Override
    public Celcius ReadTemperature()
    {
        return new Celcius(this.sensor.ObterTemperaturaEnCentigrados());
    }
}

