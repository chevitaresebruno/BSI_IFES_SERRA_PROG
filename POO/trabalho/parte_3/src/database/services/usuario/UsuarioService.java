package database.services.usuario;

import database.filters.usuario.UsuarioFilter;
import database.services.AbstractService;
import models.usuarios.Usuario;


public class UsuarioService<Model extends Usuario> extends AbstractService<Model>
{
    public UsuarioService()
    {
        super();
    }
}

