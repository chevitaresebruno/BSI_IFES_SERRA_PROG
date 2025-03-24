package brunocodes.com.controllers.dto;

public class OperacaoRequisicao
{
    private final double operando1;
    private final double operando2;
    private final String operacao;
    
    public OperacaoRequisicao(double operando1, double operando2, String operacao)
    {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacao = operacao;
    }

    public double getOperando1()
    {
        return this.operando1;
    }
    public double getOperando2()
    {
        return this.operando2;
    }

    public String getOperacao()
    {
        return this.operacao;
    }
}
