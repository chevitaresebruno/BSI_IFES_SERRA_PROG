package views.user_case.usuario;

import database.Sistema;
import database.filters.usuario.AlunoFilter;
import database.services.usuario.AlunoService;
import errors.ErroHandller;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroEntidadeNaoEncontrada;
import errors.usuario.ErroInserirSaldoNegativo;
import models.usuarios.Aluno;
import utils.SomeShortcuts;


public class UCGerenciarAluno extends UCGerenciarUsuarios<Aluno, AlunoFilter, AlunoService>
{

    @Override
    public boolean addModel(Sistema s, Aluno a)
    {
        try
        {
            return SomeShortcuts.genericAddResponse(s.getAlunoService().addData(a), "Aluno");
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
    public boolean updateModel(Sistema s, Aluno m, int where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(s.getAlunoService().updateData(m, where), "Aluno");
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
    public boolean updateModel(Sistema s, Aluno m, AlunoFilter where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(s.getAlunoService().updateData(m, where), "Aluno");
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
    public boolean deleteModel(Sistema s, int where)
    {
        try
        {
            if(s.getAlunoService().deleteData(where) != null)
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
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunoService().tamaho()));
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
            if(s.getAlunoService().deleteData(where) != null)
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
            ErroHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunoService().tamaho()));
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
            Aluno adm = s.getAlunoService().getData(where);
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
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunoService().tamaho()));
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
            Aluno adm = s.getAlunoService().getData(where);
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
        catch(ErroAtributoNulo | ErroEntidadeNaoEncontrada e)
        {
            ErroHandller.mensagemDeErro(e);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", s.getAlunoService().tamaho()));
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
        if(s.getAlunoService().tamaho() <= 0)
        {
            System.err.println("Atenção, não há Nenhum Aluno Cadastrado");
            return false;
        }
        
        for(Aluno aluno : s.getAlunoService().listData())
        {
            UCGerenciarUsuarios.imprimirUsuario(aluno);
        }
        return true;
    }

    public static double adicionarSaldo(Aluno a, double saldoExtra) throws ErroInserirSaldoNegativo
    {
        a.inserirSaldo(saldoExtra);

        return a.getSaldo();
    }
}

