package database.orders;

import java.util.Comparator;

import errors.shared.ErroAtributoNulo;

import models.pedido.Pedido;
import views.user_case.pedido.UCGerenciarCarrinho;


public class PedidoOrderBy extends BaseOrderBy<Pedido>
{
    public PedidoOrderBy(boolean ascending)
    {
        super(ascending);
    }


    @Override
    public int compare(Pedido pedido1, Pedido pedido2)
    {
        double p1Total, p2Total;

        if(pedido1.getCarrinho().size() > pedido2.getCarrinho().size())
        {
            return 1;
        }
        else if(pedido1.getCarrinho().size() < pedido2.getCarrinho().size())
        {
            return -1;
        }
        

        try
        {
            p1Total = UCGerenciarCarrinho.valorTotalCarrinho(pedido1.getCarrinho());
        }
        catch(ErroAtributoNulo e)
        {
            p1Total = 0;
        }

        try
        {
            p2Total = UCGerenciarCarrinho.valorTotalCarrinho(pedido2.getCarrinho());
        }
        catch(ErroAtributoNulo e)
        {
            return 1;
        }

        if(p1Total < p2Total)
        {
            return -1;
        }
        else if(p1Total > p2Total)
        {
            return 1;
        }

        return 0;
    }
}

