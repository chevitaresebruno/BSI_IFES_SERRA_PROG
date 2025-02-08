package database.filters.usuario;

import models.usuarios.Admin;


public class AdminFilter extends UsuarioFilter<Admin>
{
    public AdminFilter(String cpfProcurado)
    {
        super(cpfProcurado);
    }    
}

