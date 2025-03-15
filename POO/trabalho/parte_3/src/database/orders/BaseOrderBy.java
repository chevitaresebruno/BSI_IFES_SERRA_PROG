package database.orders;

import java.util.Comparator;

import models.BaseModel;


@SuppressWarnings("rawtypes")
public abstract class BaseOrderBy<Model extends BaseModel> implements Comparator<Model>
{
    private final boolean ASC;

    public BaseOrderBy(boolean ascending)
    {
        this.ASC = ascending;
    }

    public boolean orderAscendin()
    {
        return this.ASC;
    }
}

