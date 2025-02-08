package database.filters;

import java.util.List;


public abstract class BaseFilter<Model>
{
    public Model first(List<Model> models)
    {
        return models.get(this.firstIndex(models));
    }

    public abstract int firstIndex(List<Model> model);
}

