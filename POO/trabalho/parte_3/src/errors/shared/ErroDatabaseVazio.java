package errors.shared;

import errors.ErroBase;
import errors.enums.ErroNaturezaEnum;


public class ErroDatabaseVazio extends ErroBase
{
    private final String database;

    public ErroDatabaseVazio(String databaseName)
    {
        super(String.format("Erro, o databse %s está vazio", databaseName), ErroNaturezaEnum.DATABASE);

        this.database = databaseName;
    }    


    public String getDatabaseName()
    {
        return this.database;
    }
}

