package scripts.controllers.conta;

import scripts.chore.adt.Data;
import scripts.chore.conta.Conta;
import scripts.chore.conta.Corrente;
import scripts.chore.conta.Poupanca;
import scripts.chore.usuarios.Gerente;
import scripts.chore.usuarios.Pessoa;
import scripts.controllers.adt.ControllerData;
import scripts.controllers.adt.ControllerSenha;
import scripts.controllers.java_utils.ScannerHandller;
import scripts.controllers.usuarios.ControllerPessoa;

public final class ControllerConta
{
    private static Conta inputConta() throws Exception
    {
        ScannerHandller.init();

        String numero = ScannerHandller.getString("Número da conta: ");

        Pessoa titular = ControllerPessoa.inputPerson();
        Data criacao = ControllerData.inputData();

        ScannerHandller.end();

        return new Conta(numero, titular, criacao, null);
    }

    public static Corrente inputCC(Gerente gerente) throws Exception
    {
        Conta c = inputConta();

        return new Corrente(c.numero, c.titular, c.criacao, gerente);
    }

    public static Poupanca inputCP(Gerente gerente) throws Exception
    {
        Conta c = inputConta();

        return new Poupanca(c.numero, c.titular, c.criacao, c.ger);
    } 

    private static void extratoConta(Conta conta)
    {
        System.out.println("Conta: " + conta.numero);
        System.out.println("Titular: " + conta.titular.cpf);
        System.out.println("Saldo disponivel para saque: " + conta.disponivel());
    }

    public static void extrato(Corrente conta)
    {
        System.err.println("*** EXTRATO DE CONTA-CORRENTE ***");
        extratoConta(conta);
    }
    
    public static void extrato(Poupanca conta)
    {
        System.err.println("*** EXTRATO DE POUPANÇA ***");
        extratoConta(conta);
    }

    public static boolean transferir(Conta origin, Conta destination, double value, String senha) throws Exception
    {
        if(canSacar(origin, value, senha))
        {
            unsafeSacar(origin, value);
            depositar(destination, value);
            return true;
        }
        
        return false;
    }

    public static boolean canSacar(Conta conta, double value, String senha) throws Exception
    {
        if(!checkPassword(conta, senha))
        { return false; }

        if(!checkValue(value))
        { return false; }
        
        if(conta.disponivel() < value)
        {
            System.out.println("ERRO: Saque na conta " + conta.numero + " nao foi realizado. Valor disponivel: " + conta.disponivel());
            return false;
        }

        return true;
    }

    private static boolean checkPassword(Conta conta, String password) throws Exception
    {
        if(!ControllerSenha.compareSenha(conta.senha, password))
        {
            System.out.println("ERRO: A senha fornecida está incorreta");
            return false;
        }

        return true;
    }

    private static boolean checkValue(double value)
    {
        if(value <= 0)
        {
            System.out.println("ERRO: O valor fornecido deve ser maior ou igual a 0.");
            return false;
        }

        return true;
    }

    public static void depositar(Conta conta, double valor)
    {
        if(checkValue(valor))
            conta.saldo += valor;
    }

    public static double sacar(Conta conta, double valor, String senha) throws Exception
    {
        if(!canSacar(conta, valor, senha))
        { return 0.0d; }    
        
        conta.saldo -= valor;

        return conta.disponivel();
    }

    private static void unsafeSacar(Conta conta, double value)
    {
        conta.saldo -= value;
    }

    public static boolean alterarLimite(Corrente conta, String senha_gerente, String senha_conta, double limite) throws Exception
    {
        if(! ControllerPessoa.validarAcesso(conta.ger, senha_gerente))
            { return false; }
        
        if (! checkPassword(conta, senha_conta))
            { return false; }
        
        if (! checkValue(limite))
            { return false; }
        
        conta.set_limite(limite);

        return true; 
    }

    public static boolean alterarLimite(Corrente conta) throws Exception
    {
        ScannerHandller.init();

        String senha_conta = ScannerHandller.getString("Senha da Conta: ");
        String senha_gerente = ScannerHandller.getString("\nSenha do Gerente: ");
        double limite = ScannerHandller.getDouble("\nNovo Limite da Conta: ");
        
        ScannerHandller.end();

        return alterarLimite(conta, senha_conta, senha_gerente, limite);
    }
}
