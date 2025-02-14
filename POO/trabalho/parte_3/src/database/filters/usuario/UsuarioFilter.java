package database.filters.usuario;


import database.filters.BaseFilter;
import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.List;
import models.usuarios.Usuario;


public class UsuarioFilter<Model extends Usuario> extends BaseFilter<Model>
{
    private String cpf = "";

    public UsuarioFilter(String cpfProcurado)
    {
        this.cpf = cpfProcurado;
    }    

    @Override
    public int firstIndex(List<Model> usuarios) throws ErroEntidadeNaoEncontrada
    {
        if(this.cpf.length() > 0)
        {
            return this.firstCpf(usuarios);
        }

        throw new ErroEntidadeNaoEncontrada(usuarios.getClass().toString());
    }

    private int firstCpf(List<Model> usuarios) throws ErroEntidadeNaoEncontrada
    {
        int index = 0;

        for(Usuario usuario : usuarios)
        {
            if(usuario.getCPF().equals(this.cpf))
            {
                return index;
            }
            index ++;
        }

        throw new ErroEntidadeNaoEncontrada(String.format("Usuario: %s", cpf));
    }
}

