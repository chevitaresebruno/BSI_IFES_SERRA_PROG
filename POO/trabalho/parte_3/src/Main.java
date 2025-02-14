
import database.Sistema;
import database.dbLoader.DatabaseLoader;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import utils.ScannerHandller;
import views.menu.MenuPrincipal;



public class Main
{
    public static void main(String[] args)
    {
        try
        {
            mainLoop();
            /* test(); */
        }
        catch(NoSuchElementException e)
        {
            System.out.println("Código interrompido, encerrando programa.");
        }
    }

    public static void mainLoop()
    {
        Sistema s = new Sistema();

        try
        {
            ScannerHandller.init(false, "data/db/dados.txt", "#");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Arquivo não encontrado, database não alterado");
            return;
        }

        DatabaseLoader.load(s);

        ScannerHandller.close();

        try
        {
            ScannerHandller.init(false, "data/inputs/entrada.txt", "#");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Arquivo de entrada não encontrado. Iniciando scanner com input do terminal.");
            ScannerHandller.init();
        }

        MenuPrincipal.menu(s);

        ScannerHandller.close();
    }

    public static void test()
    {

    }
}

