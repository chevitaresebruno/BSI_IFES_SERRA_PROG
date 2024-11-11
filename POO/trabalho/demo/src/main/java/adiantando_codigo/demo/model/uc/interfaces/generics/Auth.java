package adiantando_codigo.demo.model.uc.interfaces.generics;

import adiantando_codigo.demo.model.domain.users.auth.Password;

public interface Auth
{
    public boolean login(Password src, String other) throws Exception;
}
