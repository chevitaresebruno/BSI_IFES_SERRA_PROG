package scripts.chore.usuarios;

import scripts.chore.adt.Data;
import scripts.controllers.adt.ControllerData;

public class Pessoa {
    public String nome;
    public Data dtNasc;
    public char genero;
    public String cpf;

    public Pessoa(String nome, Data dtNasc, char genero, String cpf)
    {
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.genero = genero;
        this.cpf = cpf;
        
        System.out.println("Nova pessoa criada no sistema.");
    }

    public int idade(Data dtHoje)
    {
        int idade;

        idade = dtHoje.ano - this.dtNasc.ano;

        if (dtHoje.mes == this.dtNasc.mes)
        {
            if (dtHoje.dia < this.dtNasc.dia)
                idade--;
        }
        else if(dtHoje.mes < this.dtNasc.mes)
            idade--;
        
        return idade;
    }

    public int idade()
    {
        int idade;
        Data dtHoje;
        
        dtHoje = ControllerData.today();

        idade = dtHoje.ano - this.dtNasc.ano;

        if (dtHoje.mes == this.dtNasc.mes)
        {
            if (dtHoje.dia < this.dtNasc.dia)
                idade--;
        }
        else if(dtHoje.mes < this.dtNasc.mes)
            idade--;
        
        return idade;
    }
}
