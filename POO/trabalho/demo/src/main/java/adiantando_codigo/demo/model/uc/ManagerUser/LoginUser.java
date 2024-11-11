package adiantando_codigo.demo.model.uc.ManagerUser;

import adiantando_codigo.demo.model.domain.users.auth.Password;
import adiantando_codigo.demo.model.uc.interfaces.generics.Auth;

public class LoginUser implements Auth
{
    @Override
    public boolean login(Password src, String other) throws Exception
    {
        return src.compare(other);
    }
    
}
