package brunocodes.com.controllers.pontos_acesso;

import brunocodes.com.controllers.dto.OperacaoRequisicao;
import brunocodes.com.controllers.dto.OperacaoResposta;
import brunocodes.com.model.serializadores.OperacaoRespostaDTO;
import brunocodes.com.model.serializadores.OperacaoSerializador;
import brunocodes.com.model.serializadores.OperacaoSolicitacaoDTO;

public final class OperacaoPontoAcesso
{
    public static OperacaoResposta executarOperacao(OperacaoRequisicao requisicao)
    {
        OperacaoRespostaDTO resposta = OperacaoSerializador.chamarOperacao(new OperacaoSolicitacaoDTO(requisicao.getOperando1(), requisicao.getOperando2(), requisicao.getOperacao()));
        
        if(resposta.deuErro())
        {
            return new OperacaoResposta(resposta.getMensagemErro());
        }
        
        return new OperacaoResposta(resposta.getValorRetorno());
    }

    public static String[] listarOperacoes()
    {
        return OperacaoSerializador.listaroperacoes();
    }
}
