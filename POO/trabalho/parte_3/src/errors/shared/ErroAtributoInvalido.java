package errors.shared;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroAtributoInvalido extends ErroBase
{
    private final String info;

    public ErroAtributoInvalido(String info)
    {
        super(String.format("Atenção, %s não constiui um valor válido", info), ErroNaturezaEnum.ATRIBUTO_INVALIDO);
        this.info = info;
    }      
    
    public String getInfo()
    {
        return this.info;
    }
}

