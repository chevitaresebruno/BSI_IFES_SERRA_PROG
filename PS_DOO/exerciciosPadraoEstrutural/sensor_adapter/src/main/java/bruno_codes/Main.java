package bruno_codes;

import bruno_codes.lib.SensorA;
import bruno_codes.lib.SensorB;
import bruno_codes.lib.SensorC;
import bruno_codes.scripts.adapter.ITemperatureSensor;
import bruno_codes.scripts.adapter.SensorAAdapter;
import bruno_codes.scripts.adapter.SensorBAdapter;
import bruno_codes.scripts.adapter.SensorCAdapter;
import bruno_codes.scripts.adt.Celcius;

public class Main {
    public static void main(String[] args)
    {
        SensorA sAInstance = new SensorA();
        SensorB sBInstance = new SensorB();
        SensorC sCInstance = new SensorC();

        ITemperatureSensor adapter = new SensorAAdapter(sAInstance);
        printTemperature(adapter.ReadTemperature());
        
        adapter = new SensorBAdapter(sBInstance);
        printTemperature(adapter.ReadTemperature());

        adapter = new SensorCAdapter(sCInstance);
        printTemperature(adapter.ReadTemperature());
    }

    public static void printTemperature(Celcius t)
    {
        System.out.println(String.format("%.2f", t.getCelcius()));
    }
}