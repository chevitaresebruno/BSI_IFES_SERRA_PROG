package abstract_types;

import java.util.Calendar;


public class Data {
    public int dia;
    public int mes;
    public int ano;

    public Data(int dia, int mes, int ano)
    {
        if(dia < 0 || dia > 31 || mes < 0 || mes > 12 || ano < 1900)
        {
            System.err.println("ERRO: Parâmetros inválidos para o construtor da classe Data");
            System.exit(1);
        }
        
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public void imprimir()
    {
        System.out.println(String.format("%d/%d/%d", this.dia, this.mes, this.ano));
    }

    public static Data today()
    {
        Calendar c = Calendar.getInstance();

        return new Data(c.get(Calendar.DATE), c.get(Calendar.MONTH), c.get(Calendar.YEAR));
    }
}
