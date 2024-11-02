// Explicações adicionais:
// O projeto do vscode tem uma base de como organizar os arquivos, logo segui esse modelo.
// O Vscode alertava um erro amrelo que me dava toc, por isso defini os atributos como públicos.

import abstract_types.Conta;
import abstract_types.Pessoa;


public class App {
    public static void main(String[] args) throws Exception {
        Pessoa p1 = new Pessoa();
        Pessoa p2 = new Pessoa();
        Conta c1 = new Conta();
        Conta c2= new Conta();

        // Construindo pessoa 1
        p1.nome = "Maria";
        p1.idade = 18;
        p1.genero = 'F';
        p1.cpf = "123.456.789-00";

        // Construindo pessoa 2
        p2.nome = "João";
        p2.idade = 22;
        p2.genero = 'M';
        p2.cpf = "234.567.890-00";
        
        // Construindo conta 1
        c1.numero ="1234-5";
        c1.titular = p1; // a Pessoa p1 é titular desta Conta!
        c1.saldo = 100.0;
        c1.limite = 200.0;

        // Construindo conta 1
        c2.numero ="2345-6";
        c2.titular = p2; // a Pessoa p2 é titular desta Conta!
        c2.saldo = 150.0;
        c2.limite = 200.0;

        //Executando o metodo que imprime o extrato
        System.out.println(c1.saldo);
        System.out.println(c1.titular.nome);
        System.out.println("Saldo da conta " + c2.numero + ": " + c2.saldo);
    }
}
