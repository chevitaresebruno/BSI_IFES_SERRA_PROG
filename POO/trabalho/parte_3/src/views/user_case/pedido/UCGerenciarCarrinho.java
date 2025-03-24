package views.user_case.pedido;

import controller.pedido.EstocavelController;
import database.Sistema;
import errors.ErroHandller;
import errors.pedido.ErroEstoqueRemoverNegativo;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroDatabaseVazio;
import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.List;
import models.pedido.Item;
import views.entrada.Entrada;


public final class UCGerenciarCarrinho
{
    public static void adicionarItemCarrinho(List<Item> c, Sistema s)
    {
        try
        {
            Item i = Entrada.novoItem(s);
            c.add(i);
        }
        catch(ErroAtributoNulo | ErroDatabaseVazio e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch (ErroEntidadeNaoEncontrada e)
        {
            System.err.println(String.format("Atenção, %s não existe.", e.getInfoEntidade()));
        }
        catch (ErroEstoqueRemoverNegativo e)
        {
            System.err.println("Atenção, não forneça valores negativos ao remover produtos do estoque.");
        }
    }

    public static void listarItensCarrinho(List<Item> carrinho)
    {
        for(Item i : carrinho)
            System.out.println(i.toString());
    }

    public static double valorTotalCarrinho(List<Item> carrinho) throws ErroAtributoNulo
    {
        double total = 0.0d;

        for(Item i : carrinho)
        {
            total += EstocavelController.valorTotal(i);
        }

        return total;
    }
}

