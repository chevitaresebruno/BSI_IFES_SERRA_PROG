package models.pedido;

import errors.pedido.ErroEstoqueAdicionarNegativo;
import errors.pedido.ErroEstoqueQuantidadeInvalida;
import errors.pedido.ErroEstoqueRemoverNegativo;


public class Produto implements IEstocavel
{
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

    public Produto(int qtd, double valor, String cod)
    {
        this(qtd, valor, "Produto Indefinido", cod);
    }

    public int retirarEstoque(int quantidade) throws ErroEstoqueRemoverNegativo, ErroEstoqueQuantidadeInvalida
    {
        if(quantidade < 0)
            { throw new ErroEstoqueRemoverNegativo(); }

        if(this.qtd - quantidade < 0)
            { throw new ErroEstoqueQuantidadeInvalida(this.qtd); }  

        this.qtd -= quantidade;

        return this.qtd;  // informa quanto restou em estoque
    }

    public int adicionarEstoque(int quantidade) throws ErroEstoqueAdicionarNegativo
    {
        if(quantidade < 0)
            { throw new ErroEstoqueAdicionarNegativo(); }

        this.qtd += quantidade;

        return this.qtd;  // informa novo valor em estoque
    }


    @Override
    public int getQuantidade()
    {
        return this.qtd;
    }

    @Override
    public double getValorUnitario()
    {
        return this.valor;
    }

    // TODO: Verificar se esses métodos são relevantes
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

