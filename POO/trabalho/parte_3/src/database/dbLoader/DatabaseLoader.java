package database.dbLoader;

import database.Sistema;
import errors.ErroHandller;
import errors.shared.ErroAtributoNulo;
import models.tad.Senha;
import models.usuarios.Admin;
import models.usuarios.Aluno;
import utils.ScannerHandller;


public final class DatabaseLoader
{
    private static boolean encryptedPassoword = false;

    public static void load(Sistema s)
    {
        if(!ScannerHandller.hadBeenInitialized())
        {
            System.err.println("Você não pode inicializar um database sem o ScannerHandller estar ativo");
            System.exit(1);
        }   
    
        DatabaseLoaderMetadataEnum input;
        do
        {
            input = DatabaseLoaderMetadataEnum.buildByInput();

            switch(input)
            {
                case ALUNO -> addAluno(s);
                case ADIMINISTRADOR -> addAdm(s);
                case INDEFINIDO -> System.out.println("Tipo de usuário não implementado.");
                case PASSWORD_ENCRYPTED -> encryptedPassoword = true;
                case FIM -> System.out.println("Dados carregados com sucesso");
            }
        }
        while(input != DatabaseLoaderMetadataEnum.FIM);
    }

    private static void addAluno(Sistema s)
    {
        try
        {
            s.getAlunoService().addData(new Aluno(ScannerHandller.getLine(), ScannerHandller.getLine(), getSenha()));
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
            s.getAdminsService().addData(new Admin(ScannerHandller.getLine(), ScannerHandller.getLine(), getSenha(), ScannerHandller.getLine()));
        }
        catch (ErroAtributoNulo e)
        {
            ErroHandller.imprimirMensagem(e);
            System.exit(2);
        }
    }

    private static Senha getSenha()
    {
        if(encryptedPassoword)
        {
            try
            {
                return new Senha(ScannerHandller.getLine(), ScannerHandller.getLine(), ScannerHandller.getLine());
            }
            catch(Exception e)
            {
                System.out.println("Erro ao carregar a base de dados. Remova o Metadado 'PASSWORDS ENCRYPTED' no início no arquivo 'dados.txt'");
                System.exit(0);
            }
        }
        return new Senha(ScannerHandller.getLine());
    }
}

