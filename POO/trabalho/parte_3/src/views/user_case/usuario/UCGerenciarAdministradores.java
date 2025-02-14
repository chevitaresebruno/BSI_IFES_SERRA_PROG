package views.user_case.usuario;

import database.filters.usuario.AdminFilter;
import database.services.usuario.AdministradorService;
import errors.ErroHandller;
import errors.shared.ErroAtributoNulo;
import models.usuarios.Admin;
import utils.SomeShortcuts;

public class UCGerenciarAdministradores extends UCGerenciarUsuarios<Admin, AdminFilter, AdministradorService>
{

    @Override
    public boolean addModel(AdministradorService as, Admin a)
    {
        try
        {
            return SomeShortcuts.genericAddResponse(as.addData(a), "Administrador");
        }
        catch(ErroAtributoNulo e) // TODO: Modificar isso aqui para que o JAVA entenda certo
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean updateModel(AdministradorService as, Admin m, int where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(as.updateData(m, where), "Administrador");
        }
        catch(ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean updateModel(AdministradorService as, Admin m, AdminFilter where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(as.updateData(m, where), "Administrador");
        }
        catch(ErroAtributoNulo e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteModel(AdministradorService as, int where)
    {
        try
        {
            if(as.deleteData(where) != null)
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
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", as.getAdminsService().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteModel(AdministradorService as, AdminFilter where)
    {
        try
        {
            if(as.deleteData(where) != null)
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
            ErroHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", as.tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(AdministradorService as, int where)
    {
        try
        {
            Admin adm = as.getData(where);
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
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", as.tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(AdministradorService as, AdminFilter where)
    {
        try
        {
            Admin adm = as.getData(where);
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
            ErroHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d administradores cadastrados.", as.getAdminsService().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean listModel(AdministradorService as)
    {
        if(as.getAdminsService().tamaho() <= 0)
        {
            System.err.println("Atenção, não há Nenhum Administrador Cadastrado");
            return false;
        }
        
        for(Admin admin : as.listData())
        {
            UCGerenciarUsuarios.imprimirUsuario(admin);
        }
        return true;
    }

}
