package factory;


public enum UsuarioFactoryEnum
{
    ALUNO("ALU"),
    ADMINISTRADOR("ADM");
    
    private final String tipoUsuario;

    UsuarioFactoryEnum(String tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDescricao()
    {
        return this.tipoUsuario;
    }
}

