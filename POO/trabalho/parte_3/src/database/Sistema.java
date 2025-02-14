package database;
import database.services.pedido.PedidoService;
import database.services.pedido.ProdutoService;
import database.services.tad.SalaService;
import database.services.usuario.AdministradorService;
import database.services.usuario.AlunoService;


public class Sistema  /* DATABASE */
{
    private final AlunoService alunos;
    private final ProdutoService produtos;
    private final PedidoService pedidos;
    private final SalaService salas;
    private final AdministradorService admins;

    public Sistema()
    {
        this.alunos = new AlunoService();
        this.admins = new AdministradorService();
        this.salas = new SalaService();
        
        this.produtos = new ProdutoService();

        this.pedidos = new PedidoService();
    }

    public AlunoService getAlunoService()
    {
        return this.alunos;
    }

    public AdministradorService getAdminsService()
    {
        return this.admins;
    }

    public SalaService getSalasService()
    {
        return this.salas;
    }

    public ProdutoService getProdutosService()
    {
        return this.produtos;
    }

    public PedidoService getPedidosService()
    {
        return this.pedidos;
    }

    public boolean sistemaVazio()
    {
        return this.admins.listData().isEmpty();
    }

    public String gerarCodigoProduto()
    {
        return String.format("PROD-%d", this.produtos.size()+1);
    }

    public String gerarCodigoPedido()
    {
        return String.format("PEDIDO-%d", this.pedidos.size()+1);
    }
}

