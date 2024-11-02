package scripts.controllers.java_utils;

import java.util.Scanner;

public final class ScannerHandller
{
    private static Scanner scanner;
    private static boolean initialize = false;
    private static int tail = 0;

    public static void init()
    {
        if(! initialize)
        {
            scanner = new Scanner(System.in);
            initialize = true;
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
        }
    }

    public static void end()
    {
        System.out.println(" ");
        close();
    }

    // private static void ErrorMessage(String message)
    // {
    //     System.out.println(String.format("ERROR: %s", message));
    // }

    private static void ErrorMessage()
    {
        System.out.println("ERROR: a classe ScannerHandller ainda não foi inicializada");
    }
    
    public static String getString(String message)
    {
        if(initialize)
        {
            System.out.print(message);
            return scanner.next(); 
        }

        ErrorMessage();
        return " ";
    }

    public static int getInt(String message)
    {
        if(initialize)
        {
            System.out.print(message);
            return scanner.nextInt(); 
        }

        ErrorMessage();
        return 0;
    }

    public static char getChar(String message)
    {
        return getString(message).charAt(0);
    }

    public static double getDouble(String message)
    {
        if(initialize)
        {
            System.out.print(message);
            return scanner.nextDouble();
        }

        ErrorMessage();
        return 0.0d;
    }

    public static String fastGetString(String message)
    {
        String output;

        init();
        output = getString(message);
        end();

        return output;
    }
}
