package brunocodes.com.model.serializadores;

public class OperacaoRespostaDTO
{
    private final double valorRetorno;
    private final String msg;
    
    public OperacaoRespostaDTO(double valor)
    {
        this.valorRetorno = valor;
        this.msg = null;
    }

    public OperacaoRespostaDTO(String mensagemErro)
    {
        this.valorRetorno = 0;
        this.msg = mensagemErro;
    }

    public double getValorRetorno()
    {
        return this.valorRetorno;
    }

    public String getMensagemErro()
    {
        return this.msg;
    }

    public boolean deuErro()
    {
        return this.msg != null;
    }
}
