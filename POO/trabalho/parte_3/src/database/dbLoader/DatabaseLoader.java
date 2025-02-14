package database.dbLoader;

import database.Sistema;
import errors.ErroHandller;
import errors.shared.ErroAtributoNulo;
import models.usuarios.Admin;
import models.usuarios.Aluno;
import utils.ScannerHandller;


public final class DatabaseLoader
{
    public static void load(Sistema s)
    {
        if(!ScannerHandller.hadBeenInitialized())
        {
            System.err.println("Você não pode inicializar um database sem o ScannerHandller estar ativo");
            System.exit(1);
        }   
    
        DatabaseLoaderEnum input;
        do
        {
            input = DatabaseLoaderEnum.buildByInput();

            switch(input)
            {
                case ALUNO -> addAluno(s);
                case ADIMINISTRADOR -> addAdm(s);
                case INDEFINIDO -> System.out.println("Tipo de usuário não implementado.");
                case FIM -> System.out.println("Dados carregados com sucesso");
            }
        }
        while(input != DatabaseLoaderEnum.FIM);
    }

    private static void addAluno(Sistema s)
    {
        try
        {
            s.getAlunoService().addData(new Aluno(ScannerHandller.getLine(), ScannerHandller.getLine(), ScannerHandller.getLine()));
        }
        catch (ErroAtributoNulo e)
        {
            ErroHandller.imprimirMensagem(e);
            System.exit(2);
        }
    }

    private static void addAdm(Sistema s)
    {
        try
        {
            s.getAdminsService().addData(new Admin(ScannerHandller.getLine(), ScannerHandller.getLine(), ScannerHandller.getLine(), ScannerHandller.getLine()));
        }
        catch (ErroAtributoNulo e)
        {
            ErroHandller.imprimirMensagem(e);
            System.exit(2);
        }
    }
}

