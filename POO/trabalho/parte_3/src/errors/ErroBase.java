package errors;

import errors.enums.ErroNaturezaEnum;


public abstract class ErroBase extends Exception
{
    private final ErroNaturezaEnum natureza;

    public ErroBase(String menssagem, ErroNaturezaEnum natureza)
    {
        super(menssagem);
        this.natureza = natureza;
    }

    @Override
    public String toString()
    {
        return String.format("-- Menssagem de Erro --\nNatureza do Erro: %s\nMenssagem: %s", this.natureza.getDescricao(), this.getMessage());
    }
}

