package errors.enums;


public enum ErroNaturezaEnum
{
    ATRIBUTO_NULO("Atributo Nulo"),
    ATRIBUTO_INVALIDO("Atributo Inválido"),
    ATRIBUTO_ERRADO("Atributo Errado"),
    PERMISSAO_DE_ACESSO("Permissão de Acesso"),
    DATABASE("Erro no Database"),
    ERRO_GRAVE_NAO_IDENTIFICADO("Erro Grave Não Identificado");

    private final String descricao;

    ErroNaturezaEnum(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDescricao()
    {
        return this.descricao;
    }
}

