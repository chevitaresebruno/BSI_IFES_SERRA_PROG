package database.filters.usuario;


import database.filters.BaseFilter;
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
    public int firstIndex(List<Model> usuarios)
    {
        if(this.cpf.length() > 0)
        {
            return this.firstCpf(usuarios);
        }

        return -1;
    }

    private int firstCpf(List<Model> usuarios)
    {
        int index = 0;

        for(Usuario usuario : usuarios)
        {
            index ++;
            if(usuario.getCPF().equals(this.cpf))
            {
                return index;
            }
        }

        return -1;
    }
}

