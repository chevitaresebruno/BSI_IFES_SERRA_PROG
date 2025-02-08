package modules.pedido;


public class Item {
    private final Produto p;
    private final int qtd;

    public Item(Produto p, int qtd)
    {
        this.p = p;
        this.qtd = qtd;
    }

    public double valorTotal()
    {
        return this.p.getValor()*this.qtd;
    }

    public Produto getProduto()
    {
        return this.p;
    }
    
    public int getQtd()
    {
        return this.qtd;
    }

    @Override
    public String toString()
    {
        return String.format("%s: %s (QTD: %d)", this.p.getCod(), this.p.getName(), this.qtd);
    }
}