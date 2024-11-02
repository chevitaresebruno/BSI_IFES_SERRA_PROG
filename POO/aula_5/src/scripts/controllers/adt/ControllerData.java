package scripts.controllers.adt;

import java.util.Calendar;
import scripts.chore.adt.Data;
import scripts.controllers.java_utils.ScannerHandller;


public final class ControllerData
{
    public static void imprimir(Data data)
    {
        imprimir(data, "dd-mm-aaaa");
    }

    public static void imprimir(Data data, String format)
    {
        String[] aux = format.split("-");
        int length = aux.length - 1;

        switch (length)
        {
            case 2 -> { }
            
            default -> {
                System.out.println("ERRO: Atenção, a string de formatação deve estar dividida em três partes com o caractere -");
                return;            
            }
        }

        for(int i = 0; i <= length; i++)
        {
            if(aux[i].compareTo("dd") == 0)
                {
                    if(data.dia < 10)
                        { System.out.print("0"); }
                    System.out.print(String.format("%d/", data.dia));
                }
            else if(aux[i].compareTo("mm") == 0)
                {
                    if(data.mes < 10)
                        { System.out.print("0"); }
                    System.out.print(String.format("%d/", data.mes));
                }
            else if(aux[i].compareTo("aaaa") == 0)
                { System.out.print(String.format("%d/", data.ano)); }
            else
                { System.out.println(String.format("\nERRO: string de formatação %s não implementada", aux[i])); return; }
        }

        System.out.println("\b ");
    }

    public static Data today()
    {
        Calendar c = Calendar.getInstance();

        return new Data(c.get(Calendar.DATE), c.get(Calendar.MONTH)+1, c.get(Calendar.YEAR));
    }

    public static Data inputData()
    {
        int day, month, year;

        ScannerHandller.init();

        System.out.println("Data de Nascimento");

        day = ScannerHandller.getInt("Dia: ");
        month = ScannerHandller.getInt("Mês: ");
        year = ScannerHandller.getInt("Ano: ");

        ScannerHandller.end();

        return new Data(day, month, year);
    }
}
