package frontend.views.console;

import frontend.controller.BaseError;
import frontend.controller.TITPController;
import frontend.controller.OTPController;



public class ViewCalculadora extends AbstractConsoleView
{
    private final String[] operacoes;

    public ViewCalculadora()
    {
        super();

        this.operacoes = TITPController.pegarOperacoes();
    }


    @Override
    protected void imprimirOpcoes()
    {
        int i;

        System.out.println("Opções:");
        for(i = 0; i < this.operacoes.length; i++)
        {
            System.out.print(String.format("%d - %s;\n", i, this.operacoes[i]));
        }
        System.out.println(String.format("%d - sair.", i));
    }

    @Override
    protected int menu()
    {
        try
        {
            this.executarOperacao(this.operacoes[this.pegarOpcaoMenu()]);
        }
        catch(IndexOutOfBoundsException e)
            { return 0; }

        return -1;
    }

    private void executarOperacao(String operacao)
    {
        double op1, op2;

        System.out.print("Valor 1: ");
        op1 = this.pegarOperando();
        System.out.print("Valor 2: ");
        op2 = this.pegarOperando();

        try
        {
            System.out.println(String.format("Resultado: %.2f", OTPController.chamar(op1, op2, operacao)));
        }
        catch(BaseError e)
        {
            e.printMsg();
        }
    }
}
