package scripts.chore.adt;


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

    public int maior(Data data)
    {
        if(this.ano > data.ano) { return 1; }
        else if(this.ano < data.ano) { return -1; }

        if(this.mes > data.mes) { return 1; }
        else if(this.mes < data.mes) { return -1; }

        if(this.dia > data.dia) { return 1; }
        else if(this.dia < data.dia) { return -1; }

        return 0;
    }
}
