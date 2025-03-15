package database.services;

import database.filters.BaseFilter;
import database.orders.BaseOrderBy;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroDatabaseVazio;
import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import models.BaseModel;


@SuppressWarnings("rawtypes")
public abstract class AbstractService<Model extends BaseModel>
{
    protected List<Model> data;    
    protected String databaseName;

    public AbstractService(String databaseName)
    {
        this.data = new LinkedList<>();
        this.databaseName = databaseName;
    }

    public AbstractService()
    {
        this("Undefined");
    }

    public List<Model> listData()
    {
        return this.data;
    }

    public List<Model> listData(BaseFilter<Model> where)
    {
        return where.all(this.data);
    }

    @SuppressWarnings("unchecked")
    public List<Model> listData(BaseFilter<Model> where, BaseOrderBy orderBy)
    {
        List<Model> lm = where.all(this.data);

        if(!lm.isEmpty())
        {
            Collections.sort(lm, orderBy);
            if(!orderBy.orderAscendin())
                { Collections.reverse(lm); }
        }

        return lm;
    }

    public Model getData(int index)
    {
        return this.data.get(index);
    }

    public Model getData(BaseFilter<Model> where) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroDatabaseVazio
    {
        if(where == null)
        {
            throw new ErroAtributoNulo("where");
        }

        if(this.data.size() <= 0)
        {
            throw new ErroDatabaseVazio(this.databaseName);
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

    public boolean updateData(Model model, BaseFilter<Model> where) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada
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

    public Model deleteData(BaseFilter<Model> where) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada
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

    public int size()
    {
        return this.data.size();
    }

    @SuppressWarnings("unchecked")
    public boolean existsInDb(Object id)
    {
        for(Model m : this.data)
        {
            if(m.unique(id))
            {
                return true;
            }
        }

        return false;
    }
}

