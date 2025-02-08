package errors.shared;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroNuncaOcorrer extends ErroBase
{
    
    public ErroNuncaOcorrer()
    {
        super("Esse erro nunca deveria ocorrer por questão de lógica. Tem algo MUITO errado.", ErroNaturezaEnum.ERRO_GRAVE_NAO_IDENTIFICADO);
    }

}

