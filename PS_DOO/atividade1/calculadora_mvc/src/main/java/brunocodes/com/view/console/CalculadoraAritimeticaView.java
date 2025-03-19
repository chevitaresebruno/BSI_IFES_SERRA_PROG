package brunocodes.com.view.console;

import brunocodes.com.controllers.pontos_acesso.ListarOperacoesPontoAcesso;

public class CalculadoraAritimeticaView extends AbstractConsoleView
{
    private final String[] operacoesDisponiveis;

    public CalculadoraAritimeticaView()
    {
        super();
        this.operacoesDisponiveis = ListarOperacoesPontoAcesso.listar();
    }

    @Override
    public void saudacoes()
    {
        System.out.println("Olá, seja bem-vindo à Calculador Aritimética!");
    }

    @Override
    public void listarOpcoes()
    {
        int i;

        System.out.println("Selecione uma opção");
        for(i=0; i < this.operacoesDisponiveis.length; i++)
        {
            System.out.println(String.format("%d-%s;", i, this.operacoesDisponiveis[i]));
        }
        System.out.println(String.format("%d-Sair.", i));
    }

    @Override
    public void despedida()
    {
        System.out.println("Até mais.");
    }

    @Override
    public String definirOperacao()
    {
        int opcao = this.pegarOpcaoMenu();

        if(opcao == this.operacoesDisponiveis.length)
        {
            return null;
        }
        else if(opcao > this.operacoesDisponiveis.length)
        {
            return "";
        }
        else
        {
            return this.operacoesDisponiveis[opcao];
        }
    }
}
