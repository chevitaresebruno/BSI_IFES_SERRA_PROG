package views.user_case;

import controller.pedido.PedidoController;
import errors.pedido.ErroEstoqueAdicionarNegativo;
import errors.pedido.ErroEstoqueQuantidadeInvalida;
import errors.shared.ErroInconscistenciaNoBD;
import errors.shared.ErroNuncaOcorrer;
import models.pedido.Pedido;
import models.usuarios.Aluno;


public final class UCGerenciarPedido
{
    public static boolean realizarEntrega(Pedido pedido, Aluno entregador) throws ErroEstoqueQuantidadeInvalida, ErroInconscistenciaNoBD, ErroNuncaOcorrer
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
}

