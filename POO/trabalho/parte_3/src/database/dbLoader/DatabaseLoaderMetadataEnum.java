package database.dbLoader;

import utils.ScannerHandller;

public enum DatabaseLoaderMetadataEnum
{
    FIM("FIM"),
    PASSWORD_ENCRYPTED("PASSWORDS ENCRYPTED"),
    INDEFINIDO("Undefined"),
    ADIMINISTRADOR("ADM"),
    ALUNO("ALU");
    
    private final String tipoUsuario;

    private DatabaseLoaderMetadataEnum(String tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario()
    {
        return tipoUsuario;
    }

    public static DatabaseLoaderMetadataEnum buildByInput()
    {
        String input = ScannerHandller.getLine();

        for(DatabaseLoaderMetadataEnum dble : values())
        {
            if(dble.tipoUsuario.equals(input))
            {
                return dble;
            }
        }

        return DatabaseLoaderMetadataEnum.INDEFINIDO;
    }
}

