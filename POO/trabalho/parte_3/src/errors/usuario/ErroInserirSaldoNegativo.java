package errors.usuario;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroInserirSaldoNegativo extends ErroBase
{
    public ErroInserirSaldoNegativo()
    {
        super("Atenção, você não pode adicionar saldo negativo a um aluno", ErroNaturezaEnum.ATRIBUTO_INVALIDO);
    }    
}

