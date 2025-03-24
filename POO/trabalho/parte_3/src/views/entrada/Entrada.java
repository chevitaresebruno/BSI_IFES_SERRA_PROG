package views.entrada;
import database.Sistema;
import database.filters.pedido.PedidoFilter;
import database.filters.pedido.ProdutoFilter;
import database.filters.tad.SalaFilter;
import errors.pedido.ErroEstoqueRemoverNegativo;
import errors.shared.ErroAtributoNulo;
import errors.shared.ErroDatabaseVazio;
import errors.shared.ErroEntidadeNaoEncontrada;
import models.pedido.Item;
import models.pedido.Pedido;
import models.pedido.Produto;
import models.tad.Sala;
import models.usuarios.Aluno;
import utils.ScannerHandller;


public final class Entrada
{
    public static Pedido novoPedido(Aluno c, Sistema sys) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroDatabaseVazio
    {
        Sala s = Entrada.lerSala(sys);

        return new Pedido(c, s);
    }

    public static Item novoItem(Sistema s) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroEstoqueRemoverNegativo, ErroDatabaseVazio
    {
        Produto prod = lerProduto(s);

        int qtd;
        do
        {
            qtd = ScannerHandller.getInt("\nQuantidade de Produtos: ");
            if(qtd <= 0)
            {
                throw new ErroEstoqueRemoverNegativo();
            }
            if(prod.getQuantidade() < qtd)        
            {
                System.err.println(String.format("Atenção, quantidade disponível em estoque: %d", prod.getQuantidade()));
                qtd = 0; /* Set Flag */
            }
        }
        while(qtd == 0);
        
        return new Item(prod, qtd);
    }

    public static Produto lerProduto(Sistema s) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroDatabaseVazio
    {
        return s.getProdutosService().getData(new ProdutoFilter(ScannerHandller.getLine("Informe Código do Produto: ")));
    }

    public static Pedido lerPedido(Sistema s) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroDatabaseVazio
    {
        return s.getPedidosService().getData(new PedidoFilter(ScannerHandller.getLine("Informe o Código do Pedido: ")));
    }

    public static Sala lerSala(Sistema s) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroDatabaseVazio
    {
        return s.getSalasService().getData(new SalaFilter(ScannerHandller.fastGetString("Informe a Sala: ")));
    }

    /*
    public Item lerItem(Sistema s) throws ErroAtributoNulo, ErroEntidadeNaoEncontrada, ErroDatabaseVazio
    {
        Produto prod = s.getProdutosService().getData(new ProdutoFilter(ScannerHandller.getLine("Digite o código do produto: ")));
        int qtd;
        do()
        {

        } = this.lerInteiro(String.format("Digite a quantidade de %s: %s no pedido: ", prod.getCod(), prod.getName()));
        
        while(prod.getQtd() < qtd)
        {
            System.err.println("Atenção, você tentou remover mais ítens do que os existentes");
            qtd = this.lerInteiro(String.format("Digite a quantidade de %s: %s no pedido: ", prod.getCod(), prod.getName()));     
        }

        return new Item(prod, qtd);
    }

    public static void listarPedidos(Aluno a, Sistema s)
    {
        ArrayList<Pedido> ps = s.filtrarPedidos(a);

        System.out.println(String.format("Pedidos de %s", a.toString()));

        for(Pedido p : ps)
        {
            System.out.println(String.format("Código do Pedido: %s", p));
            System.out.println("Produtos:");
            p.listarCarrinho();
            
            if(p.disponivel())
                { System.out.println("Status: Em Aberto"); }
            else
                { System.out.println("Status: Entregue"); }

            System.out.println(String.format("Total: %.2f", p.valorTotal()));
        }
    }
    */
}