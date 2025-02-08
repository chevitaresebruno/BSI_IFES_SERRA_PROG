package views.user_case;

import database.Sistema;
import errors.shared.ErroAtributoNulo;
import models.tad.Sala;


public final class UCGerenciarSalas
{
    public static void addSala(Sistema s, Sala sala) throws ErroAtributoNulo
    {
        s.getSalas().addData(sala);
    }
}

