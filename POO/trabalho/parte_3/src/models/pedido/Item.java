package models.pedido;


public class Item implements IEstocavel
{
    private final Produto p;
    private final int qtd;

    public Item(Produto p, int qtd)
    {
        this.p = p;
        this.qtd = qtd;
    }

    @Override
    public double getValorUnitario()
    {
        return this.p.getValorUnitario();
    }

    @Override
    public int getQuantidade()
    {
        return this.qtd;
    }

    public Produto getProduto()
    {
        return this.p;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s: %s (QTD: %d)", this.p.getCod(), this.p.getName(), this.qtd);
    }
}

