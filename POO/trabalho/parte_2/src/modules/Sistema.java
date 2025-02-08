package modules;
import java.util.ArrayList;
import modules.adt.Sala;
import modules.pedido.Pedido;
import modules.pedido.Produto;

public class Sistema {
    private final ArrayList<Aluno> alunos;
    private final ArrayList<Admin> adms;
    private final ArrayList<Produto> prods;
    private final ArrayList<Pedido> pedidos;
    private final ArrayList<Sala> salas;

    public Sistema() {
        this.alunos = new ArrayList<>();
        this.adms = new ArrayList<>();
        this.prods = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.salas = new ArrayList<>();
    }


    public void addAdmin(Admin a) {
        this.adms.add(a);
    }

    public void addAluno(Aluno a) {
        this.alunos.add(a);
    }

    public void addProd(Produto p) {
        this.prods.add(p);
    }

    public void addPedido(Pedido p) {
        this.pedidos.add(p);
    }

    public void addSala(Sala s) {
        this.salas.add(s);
    }


    public Aluno getAluno(String cpf) {
        for(Aluno a : this.alunos) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
    }

    public Admin getAdmin(String cpf) {
        for(Admin a : this.adms) {
            if (cpf.equals(a.getCPF())) return a;
        }

        return null;
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

    public Sala getSala(String nome) {
        for(Sala sala : this.salas)
            { if(nome.equals(sala.nome())) return sala; }

        return null;
    }


    public boolean sistemaVazio() {
        return this.adms.isEmpty();
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

    public void listarSalas()
    {
        System.out.println("Salas disponíveis:");
        for(Sala sala : this.salas)
            { System.out.println(sala); }
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
