package protocols.otp;


public class OperatorSenderDTO
{
    public double operando1;
    public double operando2;
    public String operacao;

    public OperatorSenderDTO(double operando1, double operando2, String operacao)
    {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacao = operacao;
    }
}

