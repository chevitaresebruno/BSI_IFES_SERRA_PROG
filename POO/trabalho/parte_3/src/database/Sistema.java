package database;
import database.services.tad.SalaService;
import database.services.usuario.AdministradorService;
import database.services.usuario.AlunoService;
import java.util.ArrayList;
import models.pedido.Pedido;
import models.pedido.Produto;
import models.usuarios.Aluno;


public class Sistema  // DATABASE
{
    private final AlunoService alunos;
    private final ArrayList<Produto> prods;
    private final ArrayList<Pedido> pedidos;
    private final SalaService salas;

    private final AdministradorService admins;

    public Sistema()
    {
        this.alunos = new AlunoService();
        this.admins = new AdministradorService();
        this.salas = new SalaService();
        
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public AlunoService getAlunos()
    {
        return this.alunos;
    }

    public AdministradorService getAdmins()
    {
        return this.admins;
    }

    public SalaService getSalas()
    {
        return this.salas;
    }

    public Produto getProd(String cod) {
        for(Produto prod : this.prods)
            { if (cod.equals(prod.getCod())) return prod; }
        
        return null;
    }

    public Pedido getPedido(String cod) {
        for(Pedido p : this.pedidos)
            { if (p.getCod().equals(cod)) return p; }
        
        return null;
    }


    public boolean sistemaVazio()
    {
        return this.admins.listData().isEmpty();
    }

    public String gerarCodigoProduto()
    {
        return String.format("PROD-%d", this.prods.size()+1);
    }

    public String gerarCodigoPedido()
    {
        return String.format("PEDIDO-%d", this.pedidos.size()+1);
    }

    public void listarProdutos()
    {
        for(Produto produto : this.prods)
            { System.out.println(produto); }
    }

    public ArrayList<Pedido> filtrarPedidos(boolean disponivel)
    {
        ArrayList<Pedido> p = new ArrayList<>();

        for(Pedido pedido : this.pedidos)
            { if(pedido.disponivel() == disponivel) p.add(pedido); }

        
        return p;
    }

    public ArrayList<Pedido> filtrarPedidos(Aluno a)
    {
        ArrayList<Pedido> p = new ArrayList<>();

        for(Pedido pedido : this.pedidos)
            { if(pedido.getCliente() == a) p.add(pedido); }

        
        return p;
    }
}

