package bruno_codes.scripts.adt;


public class Celcius
{
    private double temp;
    
    public Celcius(double temp)
    {
        this.temp = temp;
    }

    public void setTemp(double newTemp)
    {
        if(temp <= -273.15)
            { this.temp = -273.15; }
        else
            { this.temp = newTemp; }
    }
    
    public double getCelcius()
    {
        return this.temp;
    }
    
    public double toKelvin()
    {
        return this.temp + 273.15;
    }
    
    public void fromKelvin(double newTemp)
    {
        if(newTemp <= 0)
            { this.temp = 0; }
        else
            { this.temp = newTemp - 273.15; }
    }

    public double toFahrenheit()
    {
        return (this.temp*5/9) + 32;
    }

    public void fromFahrenheit(double newTemp)
    {
        this.temp = (newTemp - 32)*9/5;
    }
}

