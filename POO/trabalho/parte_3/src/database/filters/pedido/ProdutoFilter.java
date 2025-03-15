package database.filters.pedido;

import database.filters.BaseFilter;
import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.List;
import models.pedido.Produto;


public class ProdutoFilter extends BaseFilter<Produto>
{
    private final int codNumber;
    private final String completeCode;

    public ProdutoFilter(int codSearch)
    {
        super();

        this.codNumber = codSearch;
        this.completeCode = "";
    }

    public ProdutoFilter(String codSearch)
    {
        super();
        this.codNumber = -1;
        this.completeCode = codSearch;
    }

    @Override
    public int firstIndex(List<Produto> produto) throws ErroEntidadeNaoEncontrada
    {
        if(this.codNumber <= -1)
        {
            return this.searchIndexByCompleteCod(produto);
        }
        
        return this.searchIndexByCod(produto);
    }

    private int searchIndexByCod(List<Produto> produto) throws ErroEntidadeNaoEncontrada
    {
        int i = 0;
        
        for(Produto p : produto)
        {
            if(p.getCod() == this.codNumber)
            {
                return i;
            }
            i++;
        }

        throw new ErroEntidadeNaoEncontrada("Produto");
    }
    
    private int searchIndexByCompleteCod(List<Produto> produto) throws ErroEntidadeNaoEncontrada
    {
        int i = 0;
        
        for(Produto p : produto)
        {
            if(p.getCodToString().equals(this.completeCode))
            {
                return i;
            }
            i++;
        }

        System.err.println(produto.get(0).getCodToString());

        throw new ErroEntidadeNaoEncontrada("Produto");
    }
}

