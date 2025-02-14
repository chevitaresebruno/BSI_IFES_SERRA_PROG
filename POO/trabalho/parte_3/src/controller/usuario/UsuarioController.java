package controller.usuario;

import controller.ISavableInDatabase;
import models.usuarios.Usuario;


public abstract class UsuarioController<UserModel extends Usuario> implements ISavableInDatabase<UserModel>
{
    public UsuarioController()
    {

    }

    @Override
    public String toSaveDatabaseFormat(UserModel u)
    {
        return String.format("%s\n%s\n%s", u.getCPF(), u.getNome(), u.getSenhaHash());
    }    
}

