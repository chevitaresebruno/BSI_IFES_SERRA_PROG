package adiantando_codigo.demo.model.dto.user;

import adiantando_codigo.demo.model.domain.users.User;

public abstract class UserDTO
{
    public String cpf;
    public String nome;

    public UserDTO(User user)
    {
        this.cpf = user.CPFCode();
        this.nome = user.get_nome();
    }
}
