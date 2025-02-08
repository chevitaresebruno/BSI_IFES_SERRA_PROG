package modules.pedido;

import java.util.ArrayList;
import modules.Aluno;
import modules.adt.Sala;

public class Pedido {
    private final String cod;
    private final Aluno cliente;
    private Aluno entregador;
    private final Sala s;
    private final ArrayList<Item> carrinho;
    private boolean entregue;

    public Pedido(String cod, Aluno cliente, Sala s) {
        this.cod = cod;
        this.cliente = cliente;
        this.entregador = null;
        this.s = s;
        this.carrinho = new ArrayList<>();
        this.entregue = false;
    }

    public void atribuirEntregador(Aluno a)
    {
        this.entregador = a;
    }

    public boolean disponivel()
    {  // Isso aqui é isso mesmo?
        return !this.entregue;
    }

    public double valorTotal()
    {
        double total = 0;

        for(Item item : this.carrinho)
            { total += item.valorTotal(); }
        
        return total + 1;  // +1 pela taxa de entrega
    }

    public void marcarComoEntregue()
    {
        this.entregue = true;
    }

    public void confirmar()
    {
        if(this.entregue)
            return;

        for(Item item : this.carrinho)
        { item.getProduto().retirarDeEstoque(item.getQtd()); }
    }

    public String getCod()
    {
        return this.cod;
    }

    public Aluno getCliente()
    {
        return this.cliente;
    }

    public ArrayList<Item> getCarrinho()
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
