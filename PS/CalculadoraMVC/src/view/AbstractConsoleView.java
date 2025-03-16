package view;

import java.util.Scanner;

import controller.otp.OperatorSenderDTO;


public abstract class AbstractConsoleView implements IView
{
    private final Scanner scanner;

    public AbstractConsoleView()
    {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void executar()
    {
        this.saudacoes();
        this.imprimirOpcoes();
        this.menu();
        this.despedida();
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void loopInfinito()
    {
        this.saudacoes();
        this.imprimirOpcoes();
        while(this.menu() == -1);
        this.despedida();
    }

    @Override
    public void sair()
    {
        this.scanner.close();
    }

    protected void saudacoes()
    {
        System.out.println("Seja bem-vindo à Calculadora MVC.");
    }

    protected void despedida()
    {
        System.out.println("Até mais!");
    }

    protected abstract void imprimirOpcoes();
    protected abstract int menu();

    protected int pegarOpcaoMenu()
    {
        String info;

        while(true)
        {
            info = this.scanner.next();
            try
            {
                return Integer.parseInt(info);
            }
            catch(NumberFormatException e)
            {
                System.err.println("Atenção, informe somente números!");
            }
        }
    }

    protected double pegarOperando()
    {
        String info;

        while(true)
        {
            info = this.scanner.next();
            try
            {
                return Double.parseDouble(info);
            }
            catch(NumberFormatException e)
            {
                System.err.println("Atenção, informe somente números!");
            }
        }
    }

    protected OperatorSenderDTO pegarOperandos()
    {
        double op1, op2;

        System.out.print("Valor 1: ");
        op1 = this.pegarOperando();
        System.out.print("Valor 2: ");
        op2 = this.pegarOperando();

        return new OperatorSenderDTO(op1, op2);
    }
}
