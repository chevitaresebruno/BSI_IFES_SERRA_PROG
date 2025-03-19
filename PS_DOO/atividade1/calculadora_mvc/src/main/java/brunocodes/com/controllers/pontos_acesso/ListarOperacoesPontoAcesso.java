package brunocodes.com.controllers.pontos_acesso;

import java.io.File;
import java.util.ArrayList;

import brunocodes.com.config.Envoriment;

public final class ListarOperacoesPontoAcesso
{
    public static String[] listar()
    {
        ArrayList<String> lista = new ArrayList<>();
        File pasta = new File(Envoriment.DIRETORIO_BASE + "model\\operacoes");

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
