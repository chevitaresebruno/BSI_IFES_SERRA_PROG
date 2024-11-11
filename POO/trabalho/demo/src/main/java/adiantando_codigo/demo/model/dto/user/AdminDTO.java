package adiantando_codigo.demo.model.dto.user;

import adiantando_codigo.demo.model.domain.users.Admin;

public class AdminDTO extends UserDTO
{
    public String email;

    public AdminDTO(Admin admin)
    {
        super(admin);
        this.email = admin.emailText();
    }
}
