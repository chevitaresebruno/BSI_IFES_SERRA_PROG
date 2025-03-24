package database.filters.tad;

import database.filters.BaseFilter;
import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.List;
import models.tad.Sala;

public class SalaFilter extends BaseFilter<Sala>
{
    private final String codSala;

    
    public SalaFilter(String salaNameSearch)
    {
        super();    

        this.codSala = salaNameSearch;
    }

    @Override
    public int firstIndex(List<Sala> salas) throws ErroEntidadeNaoEncontrada
    {
        int i = 0;

        for(Sala s : salas)
        {
            if(s.codigo().equals(this.codSala))
            {
                return i;
            }

            i++;
        }

       throw new ErroEntidadeNaoEncontrada(salas.getClass().toString());
    }
}

