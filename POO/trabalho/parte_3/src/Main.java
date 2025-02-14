
import controller.usuario.AdminController;
import controller.usuario.AlunoController;
import database.Sistema;
import database.dbLoader.DatabaseLoader;
import database.dbWriter.DatabaseWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
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

        carregarDatabase(s);
        carregarEntradaDeDados(); /* Inicializa o ScannerHandller mas não fecha ele */
    

        MenuPrincipal.menu(s);
        
        ScannerHandller.close(); /* Fechando o ScannerHandller que foi berto em carregarEntradaDeDados */
        
        salvarBancoDeDados(s);
    }

    public static void carregarDatabase(Sistema s)
    {
        try
        {
            ScannerHandller.init(false, "dados.txt", "#");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Arquivo não encontrado, database não alterado");
            return;
        }

        DatabaseLoader.load(s);
        ScannerHandller.close();
    }

    public static void carregarEntradaDeDados()
    {
        try
        {
            ScannerHandller.init(false, "entrada.txt", "#");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Arquivo de entrada não encontrado. Iniciando scanner com input do terminal.");
            ScannerHandller.init();
        }
    }
 
    @SuppressWarnings("CallToPrintStackTrace")
    public static void salvarBancoDeDados(Sistema s)
    {
        System.out.println("Salvando Banco de Dados");

        DatabaseWriter dbw = new DatabaseWriter("dados.txt", false);

        try
        {
            DatabaseWriter.write(dbw, new AdminController(), s.getAdminsService().listData());
            DatabaseWriter.write(dbw, new AlunoController(), s.getAlunoService().listData());
            dbw.close();
        }
        catch(IOException e)
        {
            System.err.println("ERRO: não foi possível salvar as informações no arquivo de Banco de Dados");
            e.printStackTrace();
        }
    }

    public static void test()
    {

    }
}

