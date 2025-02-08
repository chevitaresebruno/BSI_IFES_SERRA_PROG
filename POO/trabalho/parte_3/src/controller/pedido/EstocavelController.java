package controller.pedido;

import errors.shared.ErroAtributoNulo;
import models.pedido.IEstocavel;


public class EstocavelController
{
    public static double valorTotal(IEstocavel objeto) throws ErroAtributoNulo
    {
        if(objeto == null)
        {
            throw new ErroAtributoNulo("objeto do tipo estocavel");
        }

        return objeto.getValorUnitario()*objeto.getQuantidade();
    }    

    public static double valorTotalCarrinho(IEstocavel carrinho[]) throws ErroAtributoNulo
    {
        double total = 0;

        if(carrinho == null)
        {
            throw new ErroAtributoNulo("carrinho");
        }

        for(IEstocavel objeto : carrinho)
        {
            if(objeto != null)
            {
                total += EstocavelController.valorTotal(objeto);
            }
        }

        return total;
    }
}

