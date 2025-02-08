package views.user_case.usuario;

import database.Sistema;
import database.filters.usuario.AdminFilter;
import database.services.usuario.AdministradorService;
import errors.ErrorHandller;
import errors.shared.ErroAtributoNulo;
import models.usuarios.Admin;
import views.utils.SomeShortcuts;

public class UCGerenciarAdministradores extends UCGerenciarUsuarios<Admin, AdminFilter, AdministradorService>
{

    @Override
    public boolean addModel(Sistema s, Admin a)
    {
        try
        {
            return SomeShortcuts.genericAddResponse(s.getAdmins().addData(a), "Administrador");
        }
        catch(ErroAtributoNulo e) // TODO: Modificar isso aqui para que o JAVA entenda certo
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean updateModel(Sistema s, Admin m, int where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(s.getAdmins().updateData(m, where), "Administrador");
        }
        catch(ErroAtributoNulo e)
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean updateModel(Sistema s, Admin m, AdminFilter where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(s.getAdmins().updateData(m, where), "Administrador");
        }
        catch(ErroAtributoNulo e)
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteModel(Sistema s, int where)
    {
        try
        {
            if(s.getAdmins().deleteData(where) != null)
            {
                return SomeShortcuts.genericDeleteResponse(true, "Administrador");
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Administrador");
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", s.getAdmins().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteModel(Sistema s, AdminFilter where)
    {
        try
        {
            if(s.getAdmins().deleteData(where) != null)
            {
                return SomeShortcuts.genericDeleteResponse(true, "Administrador");
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Administrador");
            }
        }
        catch(ErroAtributoNulo e)
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", s.getAdmins().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(Sistema s, int where)
    {
        try
        {
            Admin adm = s.getAdmins().getData(where);
            if(adm != null)
            {
                UCGerenciarUsuarios.imprimirUsuario(adm);
                return true;
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Administrador");
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", s.getAdmins().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(Sistema s, AdminFilter where)
    {
        try
        {
            Admin adm = s.getAdmins().getData(where);
            if(adm != null)
            {
                UCGerenciarUsuarios.imprimirUsuario(adm);
                return true;
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Administrador");
            }
        }
        catch(ErroAtributoNulo e)
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", s.getAdmins().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean listModel(Sistema s)
    {
        if(s.getAdmins().tamaho() <= 0)
        {
            System.err.println("Atenção, não há Nenhum Administrador Cadastrado");
            return false;
        }
        
        for(Admin admin : s.getAdmins().listData())
        {
            UCGerenciarUsuarios.imprimirUsuario(admin);
        }
        return true;
    }

}
