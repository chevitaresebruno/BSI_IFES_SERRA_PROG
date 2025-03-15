package database.services.pedido;

import database.services.AbstractService;
import errors.shared.ErroAtributoNulo;
import models.pedido.Produto;


public class ProdutoService extends AbstractService<Produto>
{
    public ProdutoService()
    {
        super();
    }

    @Override
    public boolean addData(Produto p) throws ErroAtributoNulo
    {
        if(p.getCod() <= 0 || !this.existsInDb(p.getCod()))
        {
            p.setCod(this.next());
        }

        return super.addData(p);
    }

    private boolean existsInDb(int id)
    {
        for(Produto p : this.data)
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

