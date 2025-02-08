package views.user_case.usuario;

import database.Sistema;
import database.filters.usuario.AlunoFilter;
import database.services.usuario.AlunoService;
import errors.ErrorHandller;
import errors.shared.ErroAtributoNulo;
import models.usuarios.Aluno;
import views.utils.SomeShortcuts;

public class UCGerenciarAluno extends UCGerenciarUsuarios<Aluno, AlunoFilter, AlunoService>
{

    @Override
    public boolean addModel(Sistema s, Aluno a)
    {
        try
        {
            return SomeShortcuts.genericAddResponse(s.getAlunos().addData(a), "Aluno");
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
    public boolean updateModel(Sistema s, Aluno m, int where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(s.getAlunos().updateData(m, where), "Aluno");
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
    public boolean updateModel(Sistema s, Aluno m, AlunoFilter where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(s.getAlunos().updateData(m, where), "Aluno");
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
            if(s.getAlunos().deleteData(where) != null)
            {
                return SomeShortcuts.genericDeleteResponse(true, "Aluno");
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Aluno");
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunos().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteModel(Sistema s, AlunoFilter where)
    {
        try
        {
            if(s.getAlunos().deleteData(where) != null)
            {
                return SomeShortcuts.genericDeleteResponse(true, "Aluno");
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Aluno");
            }
        }
        catch(ErroAtributoNulo e)
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunos().tamaho()));
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
            Aluno adm = s.getAlunos().getData(where);
            if(adm != null)
            {
                UCGerenciarUsuarios.imprimirUsuario(adm);
                return true;
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Aluno");
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunos().tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(Sistema s, AlunoFilter where)
    {
        try
        {
            Aluno adm = s.getAlunos().getData(where);
            if(adm != null)
            {
                UCGerenciarUsuarios.imprimirUsuario(adm);
                return true;
            }
            else
            {
                return SomeShortcuts.genericDeleteResponse(false, "Aluno");
            }
        }
        catch(ErroAtributoNulo e)
        {
            ErrorHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunos().tamaho()));
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
        if(s.getAlunos().tamaho() <= 0)
        {
            System.err.println("Atenção, não há Nenhum Aluno Cadastrado");
            return false;
        }
        
        for(Aluno aluno : s.getAlunos().listData())
        {
            UCGerenciarUsuarios.imprimirUsuario(aluno);
        }
        return true;
    }

}
