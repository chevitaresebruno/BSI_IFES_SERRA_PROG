package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;


public final class ScannerHandller
{
    private static Scanner scanner;
    private static boolean initialize = false;
    private static boolean readingFile = false;
    private static int tail = 0;
    private static boolean loopChecker = true;
    private static String ignoreLine;

    @SuppressWarnings("resource") /* TODO: Entender o motivo disso */
    public static void init(boolean checkLoop, String file, String lineToIgnore) throws FileNotFoundException
    {
        if(! initialize)
        {
            if(file.length() <= 0)
                { scanner = new Scanner(System.in).useLocale(Locale.US); }
            else
            {
                scanner = new Scanner(new FileInputStream(file)).useLocale(Locale.US);
                readingFile = true;

                initialize = true;
                loopChecker = checkLoop;
                ignoreLine = lineToIgnore;
            }        
        }

        tail++;
    }

    public static void init()
    {
        if(! initialize)
        {
            scanner = new Scanner(System.in).useLocale(Locale.US);
            initialize = true;
            loopChecker = true;
            ignoreLine = "";
        }

        tail++;
    }

    public static void close()
    {
        tail--;
        if(initialize && tail == 0)
        {
            initialize = false;
            scanner.close();
            readingFile = false;
        }
    }

    public static boolean hadBeenInitialized()
    {
        return initialize;
    }

    public static void end()
    {
        System.out.println(" ");
        close();
    }

    private static void ErrorMessage()
    {
        System.err.println("ERROR: a classe ScannerHandller ainda não foi inicializada");
    }
    
    public static String getLine(String message)
    {
        if(initialize)
        {
            System.out.print(message);
            String input = null;
            
            try
            {
                do
                {
                    input = scanner.nextLine();
                }
                while(input.startsWith(ignoreLine));
            }
            catch(IllegalStateException e)
            {
                System.err.println("O Scanner foi fechado, algum erro inesperado ocorreu. Inicializando Scanner de Emergência");
                try (Scanner emergencyScanner = new Scanner(System.in).useLocale(Locale.US))
                {
                    do { input = emergencyScanner.nextLine(); } while(input.startsWith(ignoreLine));
                }                
            }
            catch(NoSuchElementException e)
            {
                scanner.close();
                scanner = new Scanner(System.in).useLocale(Locale.US);
                readingFile = false;
                return getLine();
            }

            return input; 
        }

        ErrorMessage();
        return " ";
    }

    public static String getLine()
    {
        return getLine("");
    }

    public static int getInt(String message)
    {
        do
        {
            try
            {
                String line = getLine(message);
                return Integer.parseInt(line); 
            }
            catch(NumberFormatException e)
            {
                System.err.println("Atenção, escreva um número inteiro!");
            }
        }
        while(loopChecker);

        return -1; /* TODO: melhorar isso */
    }

    public static char getChar(String message)
    {
        return getLine(message).charAt(0);
    }

    public static double getDouble(String message)
    {
        try
        {
            return Double.parseDouble(getLine(message)); 
        }
        catch(NumberFormatException e)
        {
            System.err.println("Atenção, escreva um número!");
        }

        return 0.0d;
    }

    public static String fastGetString(String message)
    {
        String output;

        init();
        output = getLine(message);
        end();

        return output;
    }

    public static int fastGetInt(String message)
    {
        int output;

        init();
        output = getInt(message);
        end();

        return output;
    }

    public static double fastGetDouble(String message)
    {
        double output;

        init();
        output = getDouble(message);
        end();

        return output;
    }
}

