package errors.pedido;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroEstoqueQuantidadeInvalida extends ErroBase
{
    private final int qtd;
    public ErroEstoqueQuantidadeInvalida(int quantidadeDisponivel)
    {
        super(String.format("Quantidade solicitada idisponível no estoque. Quantidade disponível: %d", quantidadeDisponivel), ErroNaturezaEnum.ATRIBUTO_INVALIDO);

        this.qtd = quantidadeDisponivel;
    }

    public int getQuantidadeDisponivel()
    {
        return this.qtd;
    }
}

