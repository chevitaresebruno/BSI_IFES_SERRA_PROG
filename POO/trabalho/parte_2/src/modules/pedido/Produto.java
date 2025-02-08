package modules.pedido;


public class Produto {
    private int qtd;
    private double valor;
    private String name;
    private String cod;

    public Produto(int qtd, double valor, String name, String cod)
    {
        this.qtd = qtd;
        this.valor = valor;
        this.name = name;
        this.cod = cod;
    }

    public Produto(int qtd, double valor, String cod) {
        this(qtd, valor, "Undefined Product", cod);
    }

    public int retirarDeEstoque(int qtd)
    {
        if(qtd < 0)
            { return -1; }  // o valor de qtd PRECISA ser positivo
        
        if(this.qtd - qtd < 0)
            { return -2; }  // se o valor que tentou retirar for superior ao em estoque, não reliza a operação
        
        this.qtd -= qtd;

        return this.qtd;  // informa quanto restou em estoque
    }

    public int getQtd()
    {
        return this.qtd;
    }

    public double getValor()
    {
        return this.valor;
    }

    public String getName()
    {
        return this.name;
    }
    
    public String getCod()
    {
        return this.cod;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s: %s", this.cod, this.name);
    }
}
