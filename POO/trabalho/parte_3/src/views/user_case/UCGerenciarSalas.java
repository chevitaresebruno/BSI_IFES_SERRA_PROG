package views.user_case;

import database.Sistema;
import errors.shared.ErroAtributoNulo;
import models.tad.Sala;


public final class UCGerenciarSalas
{
    public static void addSala(Sistema s, Sala sala) throws ErroAtributoNulo
    {
        s.getSalasService().addData(sala);
    }

    public static void exibirSala(Sala s)
    {
        System.out.println(String.format("Sala: %s", s.codigo()));
    }

    public static void listarSalas(Sistema s)
    {
        for(Sala sala : s.getSalasService().listData())
        {
            UCGerenciarSalas.exibirSala(sala);
        }
    }
}

