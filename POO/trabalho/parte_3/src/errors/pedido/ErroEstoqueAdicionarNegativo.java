package errors.pedido;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroEstoqueAdicionarNegativo extends ErroBase
{
    public ErroEstoqueAdicionarNegativo()
    {
        super("Atenção, você não pode adicionar um valor negativo ao estoque.", ErroNaturezaEnum.ATRIBUTO_INVALIDO);
    }
}

