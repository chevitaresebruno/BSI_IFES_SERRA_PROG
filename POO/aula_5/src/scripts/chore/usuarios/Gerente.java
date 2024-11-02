package scripts.chore.usuarios;

import scripts.chore.adt.Data;
import scripts.chore.adt.Senha;

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

    public Gerente(Pessoa pessoa, String matricula, String senha) throws Exception
    {
        super(pessoa.nome, pessoa.dtNasc, pessoa.genero, pessoa.cpf);
        this.matricula = matricula;
        this.senha = new Senha(senha);
    }
}
