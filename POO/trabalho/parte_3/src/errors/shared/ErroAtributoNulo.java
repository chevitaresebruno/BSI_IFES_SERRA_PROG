package errors.shared;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroAtributoNulo extends ErroBase
{

    public ErroAtributoNulo(String nomeAtributo)
    {
        super(String.format("O atributo %s não pode ser nulo.", nomeAtributo), ErroNaturezaEnum.ATRIBUTO_NULO);
    }
}

