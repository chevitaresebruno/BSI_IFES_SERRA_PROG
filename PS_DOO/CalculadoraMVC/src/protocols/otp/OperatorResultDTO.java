package protocols.otp;


public class OperatorResultDTO
{
    public double resultado;
    public String erro;
    

    public OperatorResultDTO(double resultado)
    {
        this.resultado = resultado;
        this.erro = null;
    }

    public OperatorResultDTO(String erro)
    {
        this.resultado = 0;
        this.erro = erro;
    }
}

