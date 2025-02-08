package database.services.usuario;

import database.filters.usuario.AdminFilter;
import models.usuarios.Admin;


public class AdministradorService extends UsuarioService<Admin, AdminFilter>
{
    public AdministradorService()
    {
        super();
    }
}

