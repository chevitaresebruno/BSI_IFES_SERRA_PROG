package scripts.usuarios;

import java.security.CryptoPrimitive;
import scripts.adt.Data;
import scripts.adt.Senha;

public class Gerente extends Pessoa
{
    public String matricula;
    public Senha senha;

    public Gerente(String nome, Data dtNasc, char genero, String cpf, String matricula, String senha) throws Exception
    {
        super(nome, dtNasc, genero, cpf);
        this.matricula = matricula;
        this.senha = new Senha(senha);
    }

    public boolean validarAcesso(String senha) throws Exception
    {
        return this.senha.validar(senha);
    }
}
