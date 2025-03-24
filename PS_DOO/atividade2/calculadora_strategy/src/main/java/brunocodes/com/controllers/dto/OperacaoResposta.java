package brunocodes.com.controllers.dto;

public class OperacaoResposta
{
    private final double valorRetorno;
    private final String msg;
    
    public OperacaoResposta(double valor)
    {
        this.valorRetorno = valor;
        this.msg = null;
    }

    public OperacaoResposta(String mensagemErro)
    {
        this.valorRetorno = 0;
        this.msg = mensagemErro;
    }

    public double getValorRetorno()
    {
        return this.valorRetorno;
    }

    public String mensagemErro()
    {
        return this.msg;
    }

    public boolean deuErro()
    {
        return this.msg != null;
    }
}
