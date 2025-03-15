package views.user_case;

import database.filters.BaseFilter;
import database.services.AbstractService;
import models.BaseModel;

@SuppressWarnings("rawtypes")
public interface UCGenericCRUD<Model extends BaseModel, ModelFilter extends BaseFilter<Model>, ModelService extends AbstractService<Model>>
{
    public boolean addModel(ModelService as, Model m);
    public boolean updateModel(ModelService as, Model m, int where);
    public boolean updateModel(ModelService as, Model m, ModelFilter where);
    public boolean deleteModel(ModelService as, int where); 
    public boolean deleteModel(ModelService as, ModelFilter where);
    public boolean getModel(ModelService as, int where); 
    public boolean getModel(ModelService as, ModelFilter where);
    public boolean listModel(ModelService as); 
}
