package errors.usuario;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroLoginInvalido extends ErroBase
{
    public ErroLoginInvalido()
    {
        super("Usuário ou senha inválidos", ErroNaturezaEnum.PERMISSAO_DE_ACESSO);
    }    
}
