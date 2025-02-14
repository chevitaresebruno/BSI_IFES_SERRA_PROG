package views.menu;

import database.Sistema;
import errors.ErroHandller;
import errors.shared.ErroAtributoInvalido;
import errors.shared.ErroAtributoNulo;
import factory.UsuarioFactory;
import models.pedido.Produto;
import models.tad.Sala;
import models.usuarios.Admin;
import utils.ScannerHandller;
import views.menu.enums.AdminViewEnum;


public final class AdminView implements IMenuView<Admin>
{
    @Override
    public void menu(Admin a, Sistema s)
    {
        System.out.println("Login Relizado com Sucesso");
        String msg = """
                     *********************
                     Escolha uma op\u00e7\u00e3o:
                     1) Cadastrar novo administrador.
                     2) Cadastrar aluno.
                     3) Cadastrar produto.
                     4) Cadastrar sala.
                     0) Logout.
                     """;
        

        AdminViewEnum op;

        ScannerHandller.init();

        do
        {
            op = AdminViewEnum.buildByInput(msg); 
            switch(op)
            {
                case AdminViewEnum.QUIT -> System.out.println("Saindo da interface de Administrador");
                case AdminViewEnum.CADASTRAR_ADIMINISTRADOR -> cadAdmin(s);
                case AdminViewEnum.CADASTRAR_ALUNO -> cadAluno(s);
                case AdminViewEnum.CADASTRAR_PRODUTO -> cadProduto(s);
                case AdminViewEnum.CADASTRAR_SALA -> cadSala(s);
                default -> System.out.println("Opção inválida. Tente novamente: ");
            }
        }
        while (op != AdminViewEnum.QUIT);

        ScannerHandller.close();
    }

    protected static void cadAdmin(Sistema s)
    {
        System.out.println("Cadastrando Administrador");
        
        try
        {
            s.getAdminsService().addData(UsuarioFactory.criar(ScannerHandller.getLine("CPF: "), ScannerHandller.getLine("Nome: "), ScannerHandller.getLine("Senha: "), ScannerHandller.getLine("Email: ")));
        }
        catch (ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
    }

    private static void cadAluno(Sistema s)
    {
        System.out.println("Cadastrando Aluno");

        try
        {
            s.getAlunoService().addData(UsuarioFactory.criar(ScannerHandller.getLine("CPF: "), ScannerHandller.getLine("Nome: "), ScannerHandller.getLine("Senha: ")));
        }
        catch (ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
    }

    private static void cadProduto(Sistema s)
    {
        System.out.println("Cadastrando Produto");
        
        try
        {
            String nome = ScannerHandller.getLine("Nome do Produto: ");
            s.getProdutosService().addData(new Produto(ScannerHandller.getInt("Quantidade em Estoque: "), ScannerHandller.getDouble("Preço: "), nome, 0));
        }
        catch(ErroAtributoInvalido e)
        {
            System.out.println("Anteção, não informa valores menores ou iguais a 0 para esse atributo.");
        } catch (ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
    }

    private static void cadSala(Sistema s)
    {
        System.out.println("Cadastrando Sala");

        try
        {
            s.getSalasService().addData(new Sala(ScannerHandller.getLine("Bloco: "), ScannerHandller.getLine("Número: "), ScannerHandller.getLine("Andar: ")));
        } catch (ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
    }
}

