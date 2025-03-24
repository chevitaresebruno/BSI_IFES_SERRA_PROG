package controller;

import models.BaseModel;


@SuppressWarnings("rawtypes")
public interface ISavableInDatabase<Model extends BaseModel>
{
    public String toSaveDatabaseFormat(Model m);    
}

