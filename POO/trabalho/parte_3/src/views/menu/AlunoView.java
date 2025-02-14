package views.menu;

import database.Sistema;
import errors.ErroHandller;
import errors.pedido.ErroEstoqueQuantidadeInvalida;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroDatabaseVazio;
import errors.shared.ErroEntidadeNaoEncontrada;
import errors.shared.ErroInconscistenciaNoBD;
import errors.shared.ErroNuncaOcorrer;
import errors.usuario.ErroInserirSaldoNegativo;
import models.pedido.Pedido;
import models.usuarios.Aluno;
import utils.ScannerHandller;
import views.entrada.Entrada;
import views.menu.enums.AlunoViewEnum;
import views.user_case.UCGerenciarSalas;
import views.user_case.pedido.UCGerenciarCarrinho;
import views.user_case.pedido.UCGerenciarPedido;
import views.user_case.usuario.UCGerenciarAluno;


public class AlunoView implements IMenuView<Aluno>
{
    @Override
    public void menu(Aluno a, Sistema s)
    {
        AlunoViewEnum op;
        String msg = """
                     *********************
                     Escolha uma op\u00e7\u00e3o:
                     1) Fazer pedido.
                     2) Fazer entrega.
                     3) Meus pedidos.
                     4) Inserir cr\u00e9dito.
                     0) Logout.
                     """;

        do
        {
            op = AlunoViewEnum.buildByInput(msg);
            switch (op)
            {
                case AlunoViewEnum.QUIT -> { return; }
                case AlunoViewEnum.FAZER_PEDIDO -> { fazerPedido(a, s); }
                case AlunoViewEnum.ENTREGAR_PEDIDO -> { entregarPedido(a, s); }
                case AlunoViewEnum.LISTAR_PEDIDOS -> { UCGerenciarPedido.listarPedidos(a, s); }
                case AlunoViewEnum.ADICIONAR_SALDO -> { adicionarSaldo(a); }

                default -> System.out.println("Opção inválida. Tente novamente: ");
            }
        }
        while (op != AlunoViewEnum.QUIT);
    }

    private static void fazerPedido(Aluno a, Sistema s)
    {
        UCGerenciarSalas.listarSalas(s);
        
        try
        {
            Pedido p = Entrada.novoPedido(a, s);
            pedidoMenu(p, s);
            double totalCompra = valorTotalPedido(p);

            if(a.getSaldo() < totalCompra)
            {
                System.err.println("Atenção, saldo insuficiente para realizar pedido.");
            }
            else
            {
                s.getPedidosService().addData(p);
                a.retirarSaldo(totalCompra);
            }
        }
        catch(ErroAtributoNulo | ErroDatabaseVazio e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch(ErroEntidadeNaoEncontrada e)
        {
            System.err.println("Atenção, a sala fornecida não foi encontrada");
        }
    } 

    private static void pedidoMenu(Pedido p, Sistema s)
    {
        int op;
        
        String msg = """
                *********************
                Escolha uma opção:
                1) Inserir produto no carrinho.
                2) Fechar pedido.
                """;
        
        while(true)
        {
            op = ScannerHandller.getInt(msg);
            switch (op)
            {
                case 1 -> { UCGerenciarCarrinho.adicionarItemCarrinho(p.getCarrinho(), s); }
                case 2 -> { return; }
                default -> { /* Faz nada */ } 
            }
        }
    }

    private static void entregarPedido(Aluno entregador, Sistema s)
    {
        Pedido p;
        
        try
        {
            p = Entrada.lerPedido(s);
        }
        catch (ErroAtributoNulo | ErroDatabaseVazio e)
        {
            ErroHandller.mensagemDeErro(e);
            return;
        }
        catch (ErroEntidadeNaoEncontrada e)
        {
            System.err.println(String.format("Atenção, %s não encontrado", e.getInfoEntidade()));
            return;
        }


        try
        {
            if(UCGerenciarPedido.realizarEntrega(p, entregador))
            {
                System.out.println("Pedido entregue com sucesso.");
            }
            else
            {
                System.out.println("O Pedido já havia sido entregue.");
            }
        }
        catch (ErroEstoqueQuantidadeInvalida e)
        {
            System.out.println("Atenção, esse pedido não pode mais ser realizado pois não há mais produtos disponíveis. Cancele e realize outro pedido.");
        }
        catch (ErroInconscistenciaNoBD | ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch (ErroNuncaOcorrer e)
            { /* Nunca Ocorre :p */ }
    }

    private static void adicionarSaldo(Aluno a)
    {
        try
        {
            UCGerenciarAluno.adicionarSaldo(a, ScannerHandller.getDouble("Informe o saldo a ser adicionado:"));
        }
        catch (ErroInserirSaldoNegativo e)
        {
            System.err.println("Atenção, não informe saldo negativo");
        }
    }

    private static double valorTotalPedido(Pedido p) throws ErroAtributoNulo
    {
        return UCGerenciarCarrinho.valorTotalCarrinho(p.getCarrinho()) + UCGerenciarPedido.taxaDeEntrega;
    }
}

