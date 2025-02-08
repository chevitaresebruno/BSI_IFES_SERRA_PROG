package models.pedido;

import java.util.ArrayList;
import java.util.List;
import models.tad.Sala;
import models.usuarios.Aluno;

public class Pedido
{
    private final String cod;
    private final Aluno cliente;
    private Aluno entregador;
    private final Sala s;
    private final List<Item> carrinho;
    private boolean entregue;

    public Pedido(String cod, Aluno cliente, Sala s)
    {
        this.cod = cod;
        this.cliente = cliente;
        this.entregador = null;
        this.s = s;
        this.carrinho = new ArrayList<>();
        this.entregue = false;
    }

    public void setEntregador(Aluno a)
    {
        this.entregador = a;
    }

    public boolean disponivel()
    {
        return !this.entregue;
    }

    public void marcarEntregue()
    {
        this.entregue = true;
    }

    public String getCod()
    {
        return this.cod;
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

    // TODO: Mover isso para uma view ou UC
    public void listarCarrinho()
    {
        for(Item i : this.carrinho)
            System.out.println(i);
    }

    @Override
    public String toString()
    {
        return this.cod;
    }
}
