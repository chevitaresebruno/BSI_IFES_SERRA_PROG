package database.filters;

import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.List;


public abstract class BaseFilter<Model>
{
    public Model first(List<Model> models) throws ErroEntidadeNaoEncontrada
    {
        return models.get(this.firstIndex(models));
    }

    public abstract int firstIndex(List<Model> model) throws ErroEntidadeNaoEncontrada;

    public List<Model> all(List<Model> model)
    {
        throw new UnsupportedOperationException("Unimplemented method 'all'");
    }
}

