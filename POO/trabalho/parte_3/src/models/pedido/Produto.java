package models.pedido;

import errors.pedido.ErroEstoqueAdicionarNegativo;
import errors.pedido.ErroEstoqueQuantidadeInvalida;
import errors.pedido.ErroEstoqueRemoverNegativo;
import errors.shared.ErroAtributoInvalido;
import models.BaseModel;


public class Produto extends BaseModel<Integer> implements IEstocavel
{
    private int qtd;
    private double valor;
    private String name;
    private int cod;

    public Produto(int qtd, double valor, String name, int cod) throws ErroAtributoInvalido
    {
        if(qtd < 0 || valor < 0)
        {
            throw new ErroAtributoInvalido("qtd ou valor <= 0");
        }
        this.qtd = qtd;
        this.valor = valor;
        this.name = name;
        this.cod = cod;
    }

    public Produto(int qtd, double valor, int cod) throws ErroAtributoInvalido
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

    public String getName()
    {
        return this.name;
    }
    
    public int getCod()
    {
        return this.cod;
    }

    public void setCod(int newCode)
    {
        this.cod = newCode;
    }

    public String getCodToString()
    {
        return String.format("PROD-%d", this.cod);
    }
    
    @Override
    public String toString()
    {
        return String.format("%s: %s", this.cod, this.name);
    }

    @Override
    public boolean unique(Integer parameter)
    {
        return this.cod == parameter;
    }
}

