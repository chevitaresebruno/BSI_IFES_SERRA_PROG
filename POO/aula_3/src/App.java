// Explicações adicionais:
// O projeto do vscode tem uma base de como organizar os arquivos, logo segui esse modelo.
// O Vscode alertava um erro amrelo que me dava toc, por isso defini os atributos como públicos.

import abstract_types.Data;
import abstract_types.Pessoa;


public class App {
    public static void main(String[] args) throws Exception {
        Pessoa p1 = new Pessoa("Bruno", new Data(1, 1, 2000), 'M', "123.456.789-00");

        System.out.println("Idade: " + p1.idade(Data.today()));
    }
}
