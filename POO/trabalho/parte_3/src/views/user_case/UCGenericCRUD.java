package views.user_case;

import database.filters.BaseFilter;
import database.services.AbstractService;
import models.BaseModel;

@SuppressWarnings("rawtypes")
public interface UCGenericCRUD<Model extends BaseModel, ModelFilter extends BaseFilter<Model>, ModelService extends AbstractService<Model, ModelFilter>>
{
    public boolean addModel(AbstractService as, Model m);
    public boolean updateModel(AbstractService as, Model m, int where);
    public boolean updateModel(AbstractService as, Model m, ModelFilter where);
    public boolean deleteModel(AbstractService as, int where); 
    public boolean deleteModel(AbstractService as, ModelFilter where);
    public boolean getModel(AbstractService as, int where); 
    public boolean getModel(AbstractService as, ModelFilter where);
    public boolean listModel(AbstractService as); 
}
