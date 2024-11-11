package adiantando_codigo.demo.model.domain.users.composer;

public class Email
{
    private String text;

    public Email(String text)
    {
        this.text = text;
    }

    public String get_text()
    {
        return this.text.substring(0);
    }

    public void set_text(String new_text)
    {
        this.text = new_text;
    }

    public Email copy()
    {
        return new Email(this.text);
    }
}
