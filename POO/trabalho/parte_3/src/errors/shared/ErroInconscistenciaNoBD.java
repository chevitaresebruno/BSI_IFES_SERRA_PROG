package errors.shared;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;

public class ErroInconscistenciaNoBD extends ErroBase
{

    public ErroInconscistenciaNoBD()
    {
        super("Ocorreu alguma inconscistencia no banco de dados.", ErroNaturezaEnum.ERRO_GRAVE_NAO_IDENTIFICADO);
    }
    
}
