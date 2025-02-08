package modules;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import modules.adt.Sala;
import modules.adt.UsuarioDTO;
import modules.pedido.Item;
import modules.pedido.Pedido;
import modules.pedido.Produto;

public class Entrada {
    /**
     * Classe com as rotinas de entrada e saída do projeto
     * @author Hilario Seibel Junior e Bruno da Fonseca Chevitarese
     */

    public Scanner input;

    /**
     * Construtor da classe InputOutput
     * Se houver um arquivo input.txt, define que o Scanner vai ler deste arquivo.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     */
    public Entrada()
    {
        try
        {
            // Se houver um arquivo input.txt na pasta corrente, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input.txt")).useLocale(Locale.US);
            // NAO ALTERE A LOCALICAÇÃO DO ARQUIVO!!
        }
        catch (FileNotFoundException e)
        {
            // Caso contrário, vai ler do teclado.
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }

    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    private String lerLinha(String msg) {
        // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
        System.out.println(msg);
        String linha = this.input.nextLine();

        // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
        while (linha.charAt(0) == '#') linha = this.input.nextLine();
        return linha;
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    private int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        String linha = this.lerLinha(msg);
        return Integer.parseInt(linha);
    }

    /**
     * Faz a leitura de um ponto flutuante
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um ponto flutuante e retorna este número
        String linha = this.lerLinha(msg);
        return Double.parseDouble(linha);
    }



    /**********************/
    /** MENUS DO SISTEMA **/
    /**********************/

    /**
     * Exibe o menu principal até que o usuário opte por sair do programa.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Sistema s)
    {
        if (s.sistemaVazio())
        {
            System.out.println("** Inicializando o sistema **");
            this.cadAdmin(s);
        }

        String msg = """
                     *********************
                     Escolha uma op\u00e7\u00e3o:
                     1) Login.
                     0) Sair.
                     """;

        int op = this.lerInteiro(msg);

        while (op != 0)
        {
            if (op == 1) this.login(s);
            else System.out.println("Opção inválida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do administrador até que o usuário deslogue.
     * @param a: Objeto a classe Admin.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Admin a, Sistema s)
    {
        String msg = """
                     *********************
                     Escolha uma op\u00e7\u00e3o:
                     1) Cadastrar novo administrador.
                     2) Cadastrar aluno.
                     3) Cadastrar produto.
                     4) Cadastrar sala.
                     0) Logout.
                     """;

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) {cadAdmin(s);}
            if (op == 2) {cadAluno(s);}
            if (op == 3) {cadProduto(s);}
            if (op == 4) {cadSala(s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }

    /**
     * Exibe o menu do aluno até que o usuário deslogue.
     * @param a: Objeto a classe Aluno.
     * @param s: Objeto a classe Sistema.
     */
    public void menu(Aluno a, Sistema s)
    {
        String msg = """
                     *********************
                     Escolha uma op\u00e7\u00e3o:
                     1) Fazer pedido.
                     2) Fazer entrega.
                     3) Meus pedidos.
                     4) Inserir cr\u00e9dito.
                     0) Logout.
                     """;

        int op = this.lerInteiro(msg);

        while (op != 0) {
            if (op == 1) {fazerPedido(a, s);}
            if (op == 2) {entregarPedido(a, s);}
            if (op == 3) {listarPedidos(a, s);}
            if (op == 4) {inserirCredito(a, s);}
            if (op < 0 || op > 4) System.out.println("Opção inválida. Tente novamente: ");

            op = this.lerInteiro(msg);
        }
    }


    /**
     * Exibe o menu de adição de pedidos até que o usuário solicite o fechamento do Pedido.
     * @param s: Objeto da classe Sistema.
     * @param c: Ponteiro para o Carrinho do Pedido que está sendo criado.
     */
    public void menu_pedido(Sistema s, ArrayList<Item> c)
    {
        int op;
        String msg = """
                *********************
                Escolha uma opção:
                1) Inserir produto no carrinho.
                2) Fechar pedido.
                """;
        
        while(true)
        {
            op = lerInteiro(msg);
            switch (op)
            {
                case 1 -> {
                    Item i;
                    do { i = lerItem(s);} while(i == null);
                    c.add(i);
                    }
                case 2 -> { return; }
                default -> { } 
            }
        }
    }

    /* Métodos Auxiliares do Menu */
    public void login(Sistema s)
    {
        System.out.println("\nBem vindo! Digite seus dados de login:");
        String cpf = this.lerLinha("CPF: ");
        String senha = this.lerLinha("Senha: ");

        Admin adm = s.getAdmin(cpf);
        if (adm != null)
            {
                if (adm.validarAcesso(senha))
                { this.menu(adm, s); }
                else
                    System.out.println("Senha inválida.");
            }
        else
        {
            Aluno a = s.getAluno(cpf);
            if (a != null)
                {
                    if (a.validarAcesso(senha))
                    { this.menu(a, s); }
                    else
                        System.out.println("Senha inválida.");
                }
            else
                System.out.println("Usuário inexistente");
        }
    }



    /***************/
    /** CADASTROS **/
    /***************/

    public UsuarioDTO cadUsuario(int tipo, Sistema s)
    {
        switch (tipo)
        {
            case 0 -> { System.out.println("\n** Cadastrando um novo aluno **\n"); }
            case 1 -> { System.out.println("\n** Cadastrando um novo administrador **\n"); }
        }

        String cpf = this.lerLinha("Digite o cpf: ");

        switch(tipo)
        {
            case 0 -> { while (s.getAdmin(cpf) != null) cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: "); }
            case 1 -> { while (s.getAdmin(cpf) != null) cpf = this.lerLinha("Usuário já existente. Escolha outro cpf: "); }
        }

        String nome = this.lerLinha("Digite o nome: ");
        String senha = this.lerLinha("Digite a senha: ");

        return new UsuarioDTO(cpf, nome, senha);
    }

    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAdmin(Sistema s)
    {
        Admin a = cadUsuario(UsuarioDTO.ADMIN, s).toAdm(this.lerLinha("Digite o email: "));
        s.addAdmin(a);
        System.out.println("Usuário " + a + " criado com sucesso.");
    }


    /**
     * Lê os dados de um novo usuário e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadAluno(Sistema s)
    {
        Aluno a = cadUsuario(UsuarioDTO.ALUNO, s).toAluno();
        s.addAluno(a);
        System.out.println("Usuário " + a + " criado com sucesso.");
    }


    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadProduto(Sistema s)
    {
        System.out.println("\n** Cadastrando um novo Produto **\n");
        String nome = this.lerLinha("Digite o nome do produto: ");
        int qtd = this.lerInteiro("Digito a quantidade em estoque: ");
        double valor = this.lerDouble("Digito o valor unitário do produto: ");

        Produto p = new Produto(qtd, valor, nome, s.gerarCodigoProduto());
        s.addProd(p);

        System.out.println("Produto " + p + " criado com sucesso.");
    }


    /**
     * Lê os dados de um novo administrador e cadastra-a no sistema.
     * @param s: Um objeto da classe Sistema
     */
    public void cadSala(Sistema s) {
        System.out.println("\n** Cadastrando uma nova Sala **\n");
        Sala _sala;

        do
        {
            String bloco = this.lerLinha("Digite o bloco (ex: para 904T, digite 9): ");
            String sala = this.lerLinha("Digite a sala (ex: para 904T, digite 04): ");
            String andar = this.lerLinha("Digite o andar (ex: para 904T, digite T): ");
            _sala = new Sala(bloco, sala, andar);
        }
        while(s.getSala(_sala.nome()) != null);

        s.addSala(_sala);
        System.out.println("Sala " + _sala + " criada com sucesso.");
    }

    public void fazerPedido(Aluno a, Sistema s)
    {
        s.listarSalas();
        Pedido p = new Pedido(s.gerarCodigoPedido(), a, this.lerSala(s));
        this.menu_pedido(s, p.getCarrinho());

        if(p.valorTotal() <= a.getSaldo())
        {
            s.addPedido(p);
            a.retirarSaldo(p.valorTotal());
        }
        else
            System.out.println("Saldo insuficiente.");
    }

    public Sala lerSala(Sistema s)
    {
        return s.getSala(this.lerLinha("Informe a sala: "));
    }

    public Item lerItem(Sistema s)
    {
        Produto prod = s.getProd(this.lerLinha("Digite o código do produto: "));
        int qtd = this.lerInteiro(String.format("Digite a quantidade de %s: %s no pedido: ", prod.getCod(), prod.getName()));
        
        while(prod.getQtd() < qtd)
        {
            System.err.println("Atenção, você tentou remover mais ítens do que os existentes");
            qtd = this.lerInteiro(String.format("Digite a quantidade de %s: %s no pedido: ", prod.getCod(), prod.getName()));     
        }

        return new Item(prod, qtd);
    }

    public void inserirCredito(Aluno a, Sistema s)
    {
        a.inserirSaldo(this.lerDouble("Informe o saldo: "));
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

    public void entregarPedido(Aluno a, Sistema s)
    {
        String cod_ped = this.lerLinha("Informe o Código do Pedido: ");
        Pedido p = s.getPedido(cod_ped);
        
        if(p.disponivel())
        {
            p.atribuirEntregador(a);
            p.marcarComoEntregue();
            p.confirmar();
        }
    }
}