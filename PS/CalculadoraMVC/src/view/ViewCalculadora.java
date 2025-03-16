package view;

import controller.endpoints.AbstractEndpoint;
import controller.endpoints.DivisaoEndpoint;
import controller.endpoints.SomaEndpoint;
import controller.otp.OperatorResultDTO;
import controller.otp.OperatorSenderDTO;


public class ViewCalculadora extends AbstractConsoleView
{
    private final DivisaoEndpoint divisaoEntpoint;
    private final SomaEndpoint somaEndpoint;

    public ViewCalculadora()
    {
        super();
        this.divisaoEntpoint = new DivisaoEndpoint();
        this.somaEndpoint = new SomaEndpoint();
    }

    @Override
    protected void imprimirOpcoes()
    {
        System.out.println("Opções:\n0 - sair;\n1 - soma;\n2 - subtração;\n3 - multiplicação; e\n4 - divisão.");
    }

    @Override
    protected int menu()
    {
        switch(this.pegarOpcaoMenu())
        {
            case 0 -> { return 0; }
            case 1 -> this.operacaoSoma();
            case 2 -> this.operacaoSubtracao();
            case 3 -> this.operacaoMultiplicacao();
            case 4 -> this.operacaoDivisao();
            default -> System.out.println("Opção não definida!");
        }

        return -1;
    }


    private void operacaoGenerica(AbstractEndpoint endpoint, char termoOperacao)
    {
        OperatorSenderDTO values = this.getSenderValues();
        OperatorResultDTO response = endpoint.call(values);

        if(response.erro == null)
        {
            System.out.println(String.format("%.2f %c %.2f = %.2f", values.operando1, termoOperacao, values.operando2, response.resultado));
        }
        else
        {
            System.err.println(response.erro);
        }
    }

    private void operacaoSoma()
    {
        this.operacaoGenerica(this.somaEndpoint, '+');
    }

    private void operacaoSubtracao()
    {
        System.out.println("Subtração");
    }

    private void operacaoMultiplicacao()
    {
        System.out.println("Multiplicação");
    }

    private void operacaoDivisao()
    {
        operacaoGenerica(this.divisaoEntpoint, '/');
    }

    private OperatorSenderDTO getSenderValues()
    {
        double op1, op2;

        System.out.print("Valor 1: ");
        op1 = this.pegarOperando();
        System.out.print("Valor 2: ");
        op2 = this.pegarOperando();

        return new OperatorSenderDTO(op1, op2);
    }
}
