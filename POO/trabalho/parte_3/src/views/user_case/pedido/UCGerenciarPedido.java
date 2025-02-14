package views.user_case.pedido;

import controller.pedido.PedidoController;
import database.Sistema;
import database.filters.pedido.PedidoFilter;
import database.orders.PedidoOrderBy;
import errors.ErroHandller;
import errors.pedido.ErroEstoqueAdicionarNegativo;
import errors.pedido.ErroEstoqueQuantidadeInvalida;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroInconscistenciaNoBD;
import errors.shared.ErroNuncaOcorrer;
import java.util.List;
import models.pedido.Pedido;
import models.usuarios.Aluno;


public final class UCGerenciarPedido
{
    public static double taxaDeEntrega = 1.0d;

    public static boolean realizarEntrega(Pedido pedido, Aluno entregador) throws ErroEstoqueQuantidadeInvalida, ErroInconscistenciaNoBD, ErroNuncaOcorrer, ErroAtributoNulo
    {
        if(!pedido.disponivel())
        {
            return false;
        }

        try
        {
            PedidoController.decrementarPedidoEstoque(pedido);
        }
        catch (ErroEstoqueAdicionarNegativo e)
        {
            throw new ErroNuncaOcorrer();
        }
        catch (ErroInconscistenciaNoBD | ErroEstoqueQuantidadeInvalida e)
        {
            throw e;
        }

        if(PedidoController.atribuirEntregador(pedido, entregador))
        {
            pedido.marcarEntregue();
            return true;
        }

        try
        {
            PedidoController.pedidoRollback(pedido);
        }
        catch(ErroEstoqueAdicionarNegativo erro)
            { /* Faz nada */ }

        throw new ErroNuncaOcorrer();
    }

    public static void listarPedidos(Aluno a, Sistema s)
    {
        List<Pedido> pedidos = s.getPedidosService().listData(new PedidoFilter(a), new PedidoOrderBy(false));
        if(pedidos.size() <= 0)
        {
            System.out.println(String.format("%s Sem Pedidos.", a.toString()));
            return;
        }

        System.out.println(String.format("Pedidos de %s", a.toString()));

        for(Pedido p : pedidos)
        {
            System.out.println(String.format("Código do Pedido: %s", p.getCodToString()));
            System.out.println("Produtos:");
            UCGerenciarCarrinho.listarItensCarrinho(p.getCarrinho());
            
            if(p.disponivel())
                { System.out.println("Status: Em Aberto"); }
            else
                { System.out.println("Status: Entregue"); }

            try
            {
                System.out.println(String.format("Total: %.2f", UCGerenciarCarrinho.valorTotalCarrinho(p.getCarrinho()) + taxaDeEntrega));
            }
            catch(ErroAtributoNulo e)
            {
                ErroHandller.imprimirMensagem(e);
            }
        }
    }
}

