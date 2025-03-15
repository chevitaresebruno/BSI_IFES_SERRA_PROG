package errors.pedido;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroEstoqueRemoverNegativo extends ErroBase
{
    public ErroEstoqueRemoverNegativo()
    {
        super("Atenção, você não pode remover um valor negativo do estoque.", ErroNaturezaEnum.ATRIBUTO_INVALIDO);
    }
}

