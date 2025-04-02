package bruno_codes.adapter;

public class Celcius
{
    private final double value;
    
    public Celcius(double value)
    {
        if(value < -273.15)
        {
            this.value = -273.15;
        }
        else
        {
            this.value = value;
        }
    }

    public double getValue()
    {
        return this.value;
    }

    public double getAsFahrenheit()
    {
        return (this.value*9/5)+32;
    }

    public double getAsKelvin()
    {
        return this.value + 273.15;
    }

    public static Celcius fromFahrenheit(double value)
    {
        if(value <= -459.67)
        {
            return new Celcius(-273.15);
        }
        else
        {
            return new Celcius((value-32)*5/9);
        }
    }

    public static Celcius fromKelvin(double value)
    {
        if(value <= 0)
        {
            return new Celcius(-273.15);
        }
        else
        {
            return new Celcius(value+273.15);
        }
    }
}
