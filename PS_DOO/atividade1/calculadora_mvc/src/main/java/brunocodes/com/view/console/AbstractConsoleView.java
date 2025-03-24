package brunocodes.com.view.console;

import java.util.Scanner;

import brunocodes.com.controllers.dto.OperacaoRequisicao;
import brunocodes.com.controllers.dto.OperacaoResposta;
import brunocodes.com.controllers.pontos_acesso.OperacaoPontoAcesso;
import brunocodes.com.view.IView;

public abstract class AbstractConsoleView implements IView
{
    private final Scanner scanner;
    
    public AbstractConsoleView()
    {
        this.scanner = new Scanner(System.in);
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void exibir()
    {
        OperacaoRequisicao requisicao;
        OperacaoResposta respota;
        String operacao;

        this.saudacoes();
        this.listarOpcoes();
        while(true)
        {
            operacao = this.definirOperacao();

            if(operacao == null)
                { break; }
            
            requisicao = this.construirRequisicao(operacao);
            respota = OperacaoPontoAcesso.executarOperacao(requisicao);

            if(respota.deuErro())
            {
                System.err.println(respota.mensagemErro());
            }
            else
            {
                System.out.println(String.format("%.2f",respota.getValorRetorno()));
            }
        }
        this.despedida();
    }

    public int pegarOpcaoMenu()
    {
        String opcao;

        while(true)
        {
            opcao = scanner.nextLine();

            try
            {
                return Integer.parseInt(opcao);
            }
            catch(NumberFormatException e)
            {
                System.err.println("Atenção, digite somente números.");
            }
        }
    }

    public double pegarOperando(String msg)
    {
        String valor;
        System.out.print(msg);

        while(true)
        {
            valor = this.scanner.nextLine();

            try
            {
                return Double.parseDouble(valor);
            }
            catch(NumberFormatException e)
            {
                System.err.println("Atenção, digite somente números");
            }
        }
    }

    public double pegarOperando()
    {
        return this.pegarOperando("");
    }

    public OperacaoRequisicao construirRequisicao(String operacao)
    {
        double op1, op2;

        op1 = this.pegarOperando("Valor 1: ");
        op2 = this.pegarOperando("Valor 2: ");

        return new OperacaoRequisicao(op1, op2, operacao);
    }

    public abstract void saudacoes();
    public abstract void listarOpcoes();
    public abstract void despedida();
    public abstract String definirOperacao();
}
