package adiantando_codigo.demo.model.domain.users.composer;

public class CPF
{
    private String code;

    public CPF(String code)
    {
        this.code = code;
    }

    public String get_code()
    {
        return this.code.substring(0);
    }

    public void set_code(String new_code)
    {
        this.code = new_code;
    }

    public CPF copy()
    {
        return new CPF(this.code);
    }
}
