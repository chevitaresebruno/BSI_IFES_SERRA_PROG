package brunocodes.com.model.factory;

import java.lang.reflect.InvocationTargetException;

import brunocodes.com.config.Envoriment;
import brunocodes.com.model.erros.ErroDeSegurancaCritico;
import brunocodes.com.model.erros.OperacaoNaoImplementadaErro;
import brunocodes.com.model.operacoes.IOperacao;
import brunocodes.com.model.operacoes.Operacao;


public final class OperacaoFactory
{
    private static final String OPERACOES_PACOTE = Envoriment.PACOTE_BASE + "model.operacoes.aritmeticas.";
    private static final Operacao operacao = new Operacao();

    public static IOperacao criar(String operacao) throws OperacaoNaoImplementadaErro, ErroDeSegurancaCritico
    {
        try
        {
            OperacaoFactory.operacao.setComportamento((IOperacao)Class.forName(OPERACOES_PACOTE + operacao).getMethod("getInstance").invoke(null));
            return OperacaoFactory.operacao;
        } 
        catch (ClassNotFoundException ex)
        {
            throw new OperacaoNaoImplementadaErro(operacao);
        }
        catch (IllegalAccessException | NoSuchMethodException ex)
        {
            throw new ErroDeSegurancaCritico("Você está tentando acessar uma classe que não existe.");
        }
        catch (SecurityException ex)
        {
            throw new ErroDeSegurancaCritico("");
        }
        catch (IllegalArgumentException e)
        {
            throw new ErroDeSegurancaCritico("Argumentos inválidos para o construtor da classe");
        }
        catch (InvocationTargetException e)
        {
            throw new ErroDeSegurancaCritico("O construtor da classe arremessou o seguinte erro:\n" + e.getCause().toString());
        }
    }    
}
