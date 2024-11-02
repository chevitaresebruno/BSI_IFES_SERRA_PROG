package scripts.controllers.usuarios;

import scripts.chore.adt.Data;
import scripts.chore.usuarios.Gerente;
import scripts.chore.usuarios.Pessoa;
import scripts.controllers.adt.ControllerData;
import scripts.controllers.java_utils.ScannerHandller;

public final class ControllerPessoa
{
    public static Pessoa inputPerson() throws Exception
    {        
        ScannerHandller.init();

        String nome = ScannerHandller.getString("Nome: ");
        Data data = ControllerData.inputData();
        char genero = ScannerHandller.getChar("\nGênero: ");
        String cpf = ScannerHandller.getString("\nCPF: ");
        
        ScannerHandller.end();

        return new Pessoa(nome, data, genero, cpf);
    }
    
    public static Gerente inputGerente() throws Exception
    {
        ScannerHandller.init();

        Pessoa pessoa = inputPerson();
        String matricula = ScannerHandller.getString("\nMatrícula");

        ScannerHandller.end();

        return new Gerente(pessoa, matricula, "123456");
    } 

    public static boolean validarAcesso(Gerente gerente, String senha) throws Exception
    {
        return gerente.senha.compare(senha);
    }

    public static boolean validarAcesso(Gerente gerente) throws Exception
    {
        String senha = ScannerHandller.fastGetString("Informe a senha: ");
        
        return gerente.senha.compare(senha);
    }
}
