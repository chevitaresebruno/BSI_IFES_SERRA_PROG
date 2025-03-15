package controller.usuario;

import models.usuarios.Admin;


public final class AdminController extends UsuarioController<Admin>
{
    @Override
    public String toSaveDatabaseFormat(Admin a)
    {
        return String.format("ADM\n%s\n%s", super.toSaveDatabaseFormat(a), a.getEmail());
    }    
}

