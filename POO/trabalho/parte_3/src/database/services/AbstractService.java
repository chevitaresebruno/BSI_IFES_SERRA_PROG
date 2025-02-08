package database.services;

import database.filters.BaseFilter;
import errors.shared.ErroAtributoNulo;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractService<Model, ModelFilter extends BaseFilter<Model>>
{
    protected List<Model> data;    

    public AbstractService()
    {
        this.data = new LinkedList<>();
    }

    public List<Model> listData()
    {
        return this.data;
    }

    public Model getData(int index)
    {
        return this.data.get(index);
    }

    public Model getData(ModelFilter where) throws ErroAtributoNulo
    {
        if(where == null)
        {
            throw new ErroAtributoNulo("where");
        }

        return where.first(this.data);
    }

    public boolean addData(Model model) throws ErroAtributoNulo
    {
        if(model == null)
        {
            throw new ErroAtributoNulo("model");
        }
        
        return this.data.add(model);
    }

    public boolean updateData(Model model, int where) throws ErroAtributoNulo
    {
        if(model == null)
        {
            throw new ErroAtributoNulo("model");
        }

        this.data.set(where, model);
        return true;
    }

    public boolean updateData(Model model, ModelFilter where) throws ErroAtributoNulo
    {
        if(model == null)
        {
            throw new ErroAtributoNulo("model");
        }

        int index = where.firstIndex(this.data);

        if(index == -1)
        {
            return false;
        }

        this.data.set(index, model);
        return false;
    }

    public Model deleteData(int where)
    {
        return this.data.remove(where);
    }

    public Model deleteData(ModelFilter where) throws ErroAtributoNulo
    {
        if(where ==  null)
        {
            throw new ErroAtributoNulo("where");
        }

        int index = where.firstIndex(this.data);
        if(index == -1)
        {
            return null;
        }

        return this.data.remove(index);
    }

    public int tamaho()
    {
        return this.data.size();
    }
}

