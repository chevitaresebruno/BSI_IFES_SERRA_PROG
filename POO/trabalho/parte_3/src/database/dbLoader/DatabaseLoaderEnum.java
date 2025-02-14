package database.dbLoader;

import utils.ScannerHandller;

public enum DatabaseLoaderEnum
{
    FIM("FIM"),
    ADIMINISTRADOR("ADM"),
    ALUNO("ALU");
    
    private final String tipoUsuario;

    private DatabaseLoaderEnum(String tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTipoUsuario()
    {
        return tipoUsuario;
    }

    public static DatabaseLoaderEnum buildByInput()
    {
        String input = ScannerHandller.getLine();

        for(DatabaseLoaderEnum dble : values())
        {
            if(dble.tipoUsuario.equals(input))
            {
                return dble;
            }
        }

        return null;
    }
}

