package controller.pedido;

import errors.pedido.ErroEstoqueAdicionarNegativo;
import errors.pedido.ErroEstoqueQuantidadeInvalida;
import errors.pedido.ErroEstoqueRemoverNegativo;
import errors.shared.ErroInconscistenciaNoBD;
import models.pedido.Item;
import models.pedido.Pedido;
import models.usuarios.Aluno;


public final class PedidoController
{
    public static void decrementarPedidoEstoque(Pedido pedido) throws ErroEstoqueQuantidadeInvalida, ErroEstoqueAdicionarNegativo, ErroInconscistenciaNoBD
    {
        int i = 0;
        if(!pedido.disponivel())
        {
            return;
        }

        try
        {
            for(i=0; i < pedido.getCarrinho().size(); i++)
            {
                Item item = pedido.getCarrinho().get(i);
                item.getProduto().retirarEstoque(item.getQuantidade());
            }
        }
        catch(ErroEstoqueRemoverNegativo erro)
        {
            PedidoController.pedidoRollback(i-1, pedido);

            throw new ErroInconscistenciaNoBD();
        }
        catch(ErroEstoqueQuantidadeInvalida erro)
        {
            PedidoController.pedidoRollback(i-1, pedido);
            throw erro;
        }
    }

    public static boolean atribuirEntregador(Pedido pedido, Aluno entregador)
    {
        if(pedido.disponivel())
        {
            pedido.setEntregador(entregador);
            return true;
        }

        return false;
    }

    public static Item getItem(Pedido pedido, int indice)
    {
        return pedido.getCarrinho().get(indice);
    }

    public static void pedidoRollback(int i, Pedido p) throws ErroEstoqueAdicionarNegativo
    {
        for(int j = i; j >= 0; j--)
        {
            Item item = PedidoController.getItem(p, j);
            item.getProduto().adicionarEstoque(item.getQuantidade());
        }
    }

    public static void pedidoRollback(Pedido p) throws ErroEstoqueAdicionarNegativo
    {
        for(int i = 0; i < p.getCarrinho().size(); i--)
        {
            Item item = PedidoController.getItem(p, i);
            item.getProduto().adicionarEstoque(item.getQuantidade());
        }
    }
}

