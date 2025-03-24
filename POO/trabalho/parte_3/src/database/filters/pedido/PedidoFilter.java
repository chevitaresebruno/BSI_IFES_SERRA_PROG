package database.filters.pedido;

import database.filters.BaseFilter;
import errors.shared.ErroEntidadeNaoEncontrada;
import java.util.LinkedList;
import java.util.List;
import models.pedido.Pedido;
import models.usuarios.Aluno;


public class PedidoFilter extends BaseFilter<Pedido>
{
    private final int codNumber;
    private final String completeCode;
    private final Aluno cliente;

    public PedidoFilter(int codSearch)
    {
        super();

        this.codNumber = codSearch;
        this.completeCode = "";
        this.cliente = null;
    }

    public PedidoFilter(String codSearch)
    {
        super();
        this.codNumber = -1;
        this.completeCode = codSearch;
        this.cliente = null;
    }

    public PedidoFilter(Aluno clienteSearch)
    {
        super();
        this.codNumber = -1;
        this.completeCode = "";
        this.cliente = clienteSearch;
    }

    @Override
    public int firstIndex(List<Pedido> pedido) throws ErroEntidadeNaoEncontrada
    {
        if(this.codNumber <= -1)
        {
            return this.searchIndexByCompleteCod(pedido);
        }
        
        return this.searchIndexByCod(pedido);
    }    

    private int searchIndexByCod(List<Pedido> pedido) throws ErroEntidadeNaoEncontrada
    {
        int i = 0;
        
        for(Pedido p : pedido)
        {
            if(p.getCod() == this.codNumber)
            {
                return i;
            }
            i++;
        }

        throw new ErroEntidadeNaoEncontrada("Pedido");
    }
    
    private int searchIndexByCompleteCod(List<Pedido> pedido) throws ErroEntidadeNaoEncontrada
    {
        int i = 0;
        
        for(Pedido p : pedido)
        {
            if(p.getCodToString().equals(this.completeCode))
            {
                return i;
            }
            i++;
        }

        throw new ErroEntidadeNaoEncontrada("Pedido");
    }

    @Override
    public List<Pedido> all(List<Pedido> pedidos)
    {
        List<Pedido> lp = new LinkedList<>();

        for(Pedido p : pedidos)
        {
            if(p.getCliente().compareTo(this.cliente) == 0)
            {
                lp.add(p);
            }
        }

        return lp;
    }
}

