package adiantando_codigo.demo.model.uc.ManagerUser;

import java.util.Random;

public class LoginAdmin extends LoginUser
{
    public static int generate_hash()
    {
        return new Random().nextInt(1001);
    }
}
