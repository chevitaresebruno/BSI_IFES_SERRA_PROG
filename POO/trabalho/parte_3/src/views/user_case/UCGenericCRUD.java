package views.user_case;

import database.Sistema;
import database.filters.BaseFilter;
import database.services.AbstractService;

public interface UCGenericCRUD<Model, ModelFilter extends BaseFilter<Model>, ModelService extends AbstractService<Model, ModelFilter>>
{
    public boolean addModel(Sistema s, Model m);
    public boolean updateModel(Sistema s, Model m, int where);
    public boolean updateModel(Sistema s, Model m, ModelFilter where);
    public boolean deleteModel(Sistema s, int where); 
    public boolean deleteModel(Sistema s, ModelFilter where);
    public boolean getModel(Sistema s, int where); 
    public boolean getModel(Sistema s, ModelFilter where);
    public boolean listModel(Sistema s); 
}
