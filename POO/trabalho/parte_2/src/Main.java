// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

// O PROGRAMA ESTÁ DANDO ERRO PQ O INPUT DE DADOS ESTÁ ERRADO. ESTÁ SENDO FORNECIDO A PORRA DE UM VALOR DE NOME, KRLH.

import modules.Entrada;
import modules.Sistema;

public class Main {
    public static void main(String[] args) {
        Sistema s = new Sistema();
        Entrada e = new Entrada();

        e.menu(s);

        Entrada.listarPedidos(s.getAluno("234.567.890-00"), s);
    }
}