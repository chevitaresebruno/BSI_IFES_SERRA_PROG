package views.user_case.usuario;

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
    public boolean addModel(AlunoService as, Aluno a)
    {
        try
        {
            return SomeShortcuts.genericAddResponse(as.addData(a), "Aluno");
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
    public boolean updateModel(AlunoService as, Aluno m, int where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(as.updateData(m, where), "Aluno");
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
    public boolean updateModel(AlunoService as, Aluno m, AlunoFilter where)
    {
        try
        {
            return SomeShortcuts.genericUpdateResponse(as.updateData(m, where), "Aluno");
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
    public boolean deleteModel(AlunoService as, int where)
    {
        try
        {
            if(as.deleteData(where) != null)
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
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", as.tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean deleteModel(AlunoService as, AlunoFilter where)
    {
        try
        {
            if(as.deleteData(where) != null)
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
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", as.tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(AlunoService as, int where)
    {
        try
        {
            Aluno adm = as.getData(where);
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
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", as.tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean getModel(AlunoService as, AlunoFilter where)
    {
        try
        {
            Aluno adm = as.getData(where);
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
            System.err.println(String.format("Atenção, existem somente %d alunos cadastrados.", as.tamaho()));
        }
        catch(Exception e)
        {
            System.err.println("Erro não identificado");
            System.err.println(e);
        }

        return false;
    }

    @Override
    public boolean listModel(AlunoService as)
    {
        if(as.tamaho() <= 0)
        {
            System.err.println("Atenção, não há Nenhum Aluno Cadastrado");
            return false;
        }
        
        for(Aluno aluno : as.listData())
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

