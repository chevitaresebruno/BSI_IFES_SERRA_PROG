
import controller.pedido.ItemController;
import errors.ErrorHandller;
import errors.shared.ErroAtributoNulo;
import models.pedido.Produto;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

// O PROGRAMA ESTÁ DANDO ERRO PQ O INPUT DE DADOS ESTÁ ERRADO. ESTÁ SENDO FORNECIDO A PORRA DE UM VALOR DE NOME, KRLH.


public class Main {
    public static void main(String[] args)
    {
        // Sistema s = new Sistema();
        // Entrada e = new Entrada();

        // e.menu(s);

        // Entrada.listarPedidos(s.getAluno("234.567.890-00"), s);

        Main.test();
    }

    public static void test()
    {
        Produto p = new Produto(10, 5, null);
        ErrorHandller.desativarDebug();

        try
        {
            System.out.println(String.format("%.2f", ItemController.valorTotal(null)));
        }
        catch(ErroAtributoNulo e)
        {
            System.err.println(ErrorHandller.mensagemDeErro(e));
        }
    }
}

