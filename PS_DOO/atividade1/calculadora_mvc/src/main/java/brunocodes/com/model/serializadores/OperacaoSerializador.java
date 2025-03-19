package brunocodes.com.model.serializadores;

import brunocodes.com.model.erros.BaseError;
import brunocodes.com.model.erros.ErroDeSegurancaCritico;
import brunocodes.com.model.factory.OperacaoFactory;
import brunocodes.com.model.security.Seguranca;

public final class OperacaoSerializador
{
    public static OperacaoRespostaDTO chamarOperacao(OperacaoSolicitacaoDTO solicitacao)
    {
        try
        {
            return new OperacaoRespostaDTO(OperacaoFactory.criar(solicitacao.operacao).calcular(solicitacao.operando1, solicitacao.operando2));
        }
        catch(ErroDeSegurancaCritico e)
        {
            Seguranca.AlertaDeSeguranca();
            return new OperacaoRespostaDTO(e.getMessage());
        }
        catch(BaseError e)
        {
            return new OperacaoRespostaDTO(e.getMessage());
        }
    }
}
