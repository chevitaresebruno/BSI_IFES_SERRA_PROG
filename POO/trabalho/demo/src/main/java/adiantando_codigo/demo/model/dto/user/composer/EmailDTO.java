package adiantando_codigo.demo.model.dto.user.composer;

import adiantando_codigo.demo.model.domain.users.composer.Email;

public class EmailDTO
{
    private final String text;
    private final String domain;

    public EmailDTO(Email mail)
    {
        this.text = mail.get_text();
        this.domain = mail.get_text();
    }


}
