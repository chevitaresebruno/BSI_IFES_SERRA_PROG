package database.filters.tad;

import java.util.List;

import database.filters.BaseFilter;
import models.tad.Sala;

public class SalaFilter extends BaseFilter<Sala>
{
    @Override
    public int firstIndex(List<Sala> sala)
    {
        return 0;
    }
}

