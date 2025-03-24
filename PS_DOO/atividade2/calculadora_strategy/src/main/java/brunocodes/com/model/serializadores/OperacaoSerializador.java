package brunocodes.com.model.serializadores;

import java.io.File;
import java.util.ArrayList;

import brunocodes.com.config.Envoriment;
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

    public static String[] listaroperacoes()
    {
        ArrayList<String> lista = new ArrayList<>();
        File pasta = new File(Envoriment.DIRETORIO_BASE + "model\\operacoes\\aritmeticas");

        if (pasta.exists() && pasta.isDirectory())
        {
            File[] arquivos = pasta.listFiles((_pasta, arquivo) -> arquivo.endsWith(".java") && !arquivo.startsWith("I"));  // Pegando os arquivos que terminam com .java e ignorando interfaces
            
            if(arquivos != null)
            {
                for (File arquivo : arquivos)
                {
                    lista.add(arquivo.getName().replace(".java", "").replace("\\", ".")); // Removendo o sufixo .java e substituindo a estrutura de pastas (\\) pela estrutura de pacotes (.)
                }
            }
        }
        else
        {
            lista.add("Sem operações definidas");
        }

        return lista.toArray(String[]::new);
    }
}
