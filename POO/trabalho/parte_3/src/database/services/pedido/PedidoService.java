package database.services.pedido;

import database.filters.pedido.PedidoFilter;
import database.services.AbstractService;
import errors.shared.ErroAtributoNulo;
import models.pedido.Pedido;


public class PedidoService extends AbstractService<Pedido, PedidoFilter>
{

    public PedidoService()
    {
        super();
    }

    @Override
    public boolean addData(Pedido p) throws ErroAtributoNulo
    {
        if(p.getCod() <= 0 || !this.existsInDb(p.getCod()))
        {
            p.setCode(this.next());
        }

        return super.addData(p);
    }

    private boolean existsInDb(int id)
    {
        for(Pedido p : this.data)
        {
            if(p.getCod() == id)
            {
                return true;
            }
        }

        return false;
    }

    private int next()
    {
        if(this.data.size() <= 0)
        {
            return 1;
        }

        int _next = this.data.getLast().getCod()+1;

        return _next;
    }
}

