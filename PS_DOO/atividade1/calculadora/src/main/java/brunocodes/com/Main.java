package brunocodes.com;

import java.util.Scanner;

public class Main
{
    private static Scanner s;
    public static void main(String[] args)
    {
        s = new Scanner(System.in);
        menu();

        int operacao;

        while(true)
        {
            operacao = Integer.parseInt(pegarStringNumerica());

            switch(operacao)
            {
                case 0 -> soma();
                case 1 -> subtracao();
                case 2 -> multiplicacao();
                case 3 -> divisao();
                case 4 -> { s.close(); return; }
                default -> System.out.println("Opção não implementada.");
            }
        }
    }

    private static void menu()
    {
        System.out.println("Menu da Calculadora.\n0 - Soma;\n1 - Subtração;\n2 - Multiplicação;\n3 - Divisão;\n4 - sair.");
    }

    private static String pegarStringNumerica()
    {
        double n = -1.0;
        String v;
        
        do
        {
            v = s.nextLine();
            
            try
            { n = Double.parseDouble(v); }
            catch(NumberFormatException e)
            {
                System.out.println("Informe somente número!");
            }
        }
        while(n == -1.0);

        return v;
    }

    private static void soma()
    {
        double num1, num2;

        System.out.print("Valor 1:");
        num1 = Double.parseDouble(pegarStringNumerica());
        System.out.print("Valor 2:");
        num2 = Double.parseDouble(pegarStringNumerica());
        
        System.out.println(String.format("%.2f", num1 + num2));
    }

    private static void subtracao()
    {
        double num1, num2;

        System.out.print("Valor 1:");
        num1 = Double.parseDouble(pegarStringNumerica());
        System.out.print("Valor 2:");
        num2 = Double.parseDouble(pegarStringNumerica());
        
        System.out.println(String.format("%.2f", num1 - num2));
    }

    private static void multiplicacao()
    {
        double num1, num2;

        System.out.print("Valor 1:");
        num1 = Double.parseDouble(pegarStringNumerica());
        System.out.print("Valor 2:");
        num2 = Double.parseDouble(pegarStringNumerica());
        
        System.out.println(String.format("%.2f", num1 * num2));
    }

    private static void divisao()
    {
        double num1, num2;

        System.out.print("Valor 1:");
        num1 = Double.parseDouble(pegarStringNumerica());
        System.out.print("Valor 2:");
        num2 = Double.parseDouble(pegarStringNumerica());

        if(num2 == 0)
            { System.out.println("Impossível Dividir por 0."); return; }

        System.out.println(String.format("%.2f", num1 / num2));
    }
}