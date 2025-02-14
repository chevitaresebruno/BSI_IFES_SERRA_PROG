package models.pedido;

import java.util.ArrayList;
import java.util.List;
import models.BaseModel;
import models.tad.Sala;
import models.usuarios.Aluno;


public class Pedido extends BaseModel<Integer>
{
    private int cod;
    private final Aluno cliente;
    private Aluno entregador;
    private final Sala s;
    private final List<Item> carrinho;
    private boolean entregue;

    public Pedido(int cod, Aluno cliente, Sala s)
    {
        this.cod = cod;
        this.cliente = cliente;
        this.entregador = null;
        this.s = s;
        this.carrinho = new ArrayList<>();
        this.entregue = false;
    }

    public Pedido(Aluno cliente, Sala s)
    {
        this(0, cliente, s);
    }

    public void setEntregador(Aluno a)
    {
        this.entregador = a;
    }

    public boolean setCode(int id)
    {
        if(id <= 0)
            return false;

        this.cod = id;

        return true;
    }

    public boolean disponivel()
    {
        return !this.entregue;
    }

    public void marcarEntregue()
    {
        this.entregue = true;
    }

    public int getCod()
    {
        return this.cod;
    }

    public String getCodToString()
    {
        return String.format("PEDIDO-%s", this.cod);
    }

    public Aluno getCliente()
    {
        return this.cliente;
    }

    public List<Item> getCarrinho()
    {
        return this.carrinho;
    }

    public Aluno getEntregador()
    {
        return this.entregador;
    }

    public Sala getSala()
    {
        return this.s;
    }

    @Override
    public String toString()
    {
        return this.getCodToString();
    }

    @Override
    public boolean unique(Integer parameter)
    {
        return this.cod == parameter;
    }
}
