package errors.shared;

import errors.ErroBase;

import errors.enums.ErroNaturezaEnum;


public class ErroEntidadeNaoEncontrada extends ErroBase
{
    private final String entidade;

    public ErroEntidadeNaoEncontrada(String infoEntidade)
    {
        super(String.format("Atenção, %s não existe no banco de dados", infoEntidade), ErroNaturezaEnum.ATRIBUTO_ERRADO);
        
        this.entidade = infoEntidade;
    }
    
    public String getInfoEntidade()
    {
        return this.entidade;
    }
}

