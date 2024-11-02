// Explicações adicionais:
// O projeto do vscode tem uma base de como organizar os arquivos, logo segui esse modelo.
// O Vscode alertava um erro amrelo que me dava toc, por isso defini os atributos como públicos.

import scripts.chore.adt.Data;
import scripts.chore.conta.Corrente;
import scripts.chore.conta.Poupanca;
import scripts.chore.usuarios.Gerente;
import scripts.chore.usuarios.Pessoa;
import scripts.controllers.conta.ControllerConta;
import scripts.controllers.usuarios.ControllerPessoa;

public class App {
    public static void main(String[] args) throws Exception
    {
        Pessoa p1, p2;
        Gerente g1;
        Corrente cc1;
        Poupanca cp1;
        
        p1 = new Pessoa("Bruno", new Data(8, 10, 2004), 'M', "12345678911");
        p2 = ControllerPessoa.inputPerson();
        g1 = ControllerPessoa.inputGerente();
        cc1 = ControllerConta.inputCC(g1);
        cp1 = ControllerConta.inputCP(g1);
        
        ControllerConta.extrato(cc1);
        ControllerConta.extrato(cp1);
    }
}
