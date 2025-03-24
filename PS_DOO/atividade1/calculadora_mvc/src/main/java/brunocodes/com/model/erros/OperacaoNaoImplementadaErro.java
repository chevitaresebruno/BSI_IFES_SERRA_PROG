package brunocodes.com.model.erros;

public class OperacaoNaoImplementadaErro extends BaseError
{
    public OperacaoNaoImplementadaErro(String operacao)
    {
        super(String.format("A operação %s não foi definida.", operacao));
    }
}
