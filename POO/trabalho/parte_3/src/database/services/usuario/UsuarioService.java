package database.services.usuario;

import database.filters.usuario.UsuarioFilter;
import database.services.AbstractService;
import models.usuarios.Usuario;


public class UsuarioService<Model extends Usuario, ModelFilter extends UsuarioFilter<Model>> extends AbstractService<Model, ModelFilter>
{
    public UsuarioService()
    {
        super();
    }
}

