package model.serializers;


public class OperationSerializerSenderDTO
{
    public double operando1;
    public double operando2;
    public String operacao;
    
    public OperationSerializerSenderDTO(double operando1, double operando2, String operacao)
    {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacao = operacao;
    }
}
