package brunocodes.com.model.serializadores;

public class OperacaoSolicitacaoDTO
{
    protected double operando1;
    protected double operando2;
    protected String operacao;
    
    public OperacaoSolicitacaoDTO(double operando1, double operando2, String operacao)
    {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacao = operacao;
    }
}
