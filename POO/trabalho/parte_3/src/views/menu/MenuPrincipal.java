package views.menu;

import database.Sistema;
import database.filters.usuario.AdminFilter;
import database.filters.usuario.AlunoFilter;
import errors.ErroHandller;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroDatabaseVazio;
import errors.shared.ErroEntidadeNaoEncontrada;
import errors.usuario.ErroLoginInvalido;
import models.usuarios.Admin;
import models.usuarios.Aluno;
import utils.ScannerHandller;
import views.menu.enums.MenuPrincipalEnum;


public final class MenuPrincipal
{
    private static final AdminView amd = new AdminView();
    private static final AlunoView aluno = new AlunoView();


    public static void menu(Sistema s)
    {
        ScannerHandller.init();

        if(s.sistemaVazio())
        {
            System.out.println("** Inicializando o sistema **");
            AdminView.cadAdmin(s);
        }

        String msg = """
            *********************
            Escolha uma op\u00e7\u00e3o:
            1) Login.
            0) Sair.
            """;

        MenuPrincipalEnum op;

        do
        {
            op = MenuPrincipalEnum.buildByInput(msg);

            switch(op)
            {
                case MenuPrincipalEnum.QUIT -> { /* Faz Nada */ }
                case MenuPrincipalEnum.LOGIN -> { login(s); } 
                default -> System.out.println("Opção inválida. Tente novamente: ");
            }
        }
        while(op != MenuPrincipalEnum.QUIT);

        ScannerHandller.close();
    }   

    private static void login(Sistema s)
    {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = ScannerHandller.getLine("CPF: \n");
        String senha = ScannerHandller.getLine("Senha: \n");

        try
        {
            loginAdm(s, cpf, senha);
            return;
        }
        catch (ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
            return;
        }
        catch(ErroLoginInvalido e)
        {
            ErroHandller.imprimirMensagem(e);
            return;
        }
        catch(ErroDatabaseVazio e)
        {
            System.err.println("Atenção, não há Administradores cadastrados. Reiniciando o Sistema.");
            System.exit(0);
        }
        catch (ErroEntidadeNaoEncontrada e)
            { /* Tenta Fazer Login no Aluno */ }

        try
        {
            loginAluno(s, cpf, senha);
        }
        catch(ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch(ErroEntidadeNaoEncontrada | ErroLoginInvalido | ErroDatabaseVazio e)
        {
            ErroHandller.imprimirMensagem(e);
        }
    }

    private static void loginAdm(Sistema s, String cpf, String senha) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroLoginInvalido, ErroDatabaseVazio
    {
        Admin a = s.getAdminsService().getData(new AdminFilter(cpf));
        if(a.validarAcesso(senha))
        {
            amd.menu(a, s);
        }
        else
        {
            throw new ErroLoginInvalido();
        }
    }

    private static void loginAluno(Sistema s, String cpf, String senha) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroLoginInvalido, ErroDatabaseVazio
    {
        Aluno a = s.getAlunoService().getData(new AlunoFilter(cpf));
    
        if(a.validarAcesso(senha))
        {
            aluno.menu(a, s);
        }
        else
        {
            throw new ErroLoginInvalido();
        }
    }
}

